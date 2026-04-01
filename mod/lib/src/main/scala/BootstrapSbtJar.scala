package sabotage.lib

import java.nio.file.{Path, Paths}

import language.experimental.saferExceptions

object BootstrapSbtJar:

  def bootstrap(
      launcherVersion: String
  )(using Network, Files, Proc, Log, Env): Path throws (Network.Err) =
    Timings.time("bootstrapping sbt jar"):
      bootstrapImpl(launcherVersion)

  private def bootstrapImpl(
      launcherVersion: String
  )(using
      Network,
      Files,
      Proc,
      Log,
      Env,
      CanThrow[Network.Err]
  ): Path =
    val jar = jarUrl(launcherVersion)
    val sha = jar + ".sha1"
    val downloadLocation =
      Env.userHome.resolve(
        s".cache/sbt/boot/sbt-launch/$launcherVersion/sbt-launch-$launcherVersion.jar"
      )

    if Files.isFile(downloadLocation) then downloadLocation
    else
      Files.createDirectories(downloadLocation.getParent())
      val cleanup = List.newBuilder[Path]
      try
        val shaLocation = Paths.get(downloadLocation.toString + ".sha1")
        val tempLocation = Paths.get(downloadLocation.toString + ".temp")
        Log.info(
          s"downloading sbt launcher $launcherVersion from [$jar] to [$tempLocation]"
        )
        Network.downloadFile(jar, tempLocation)
        cleanup += tempLocation

        Network.downloadFile(sha, shaLocation)
        cleanup += shaLocation

        IMPROVE("Avoid shelling out to shasum")
        if Proc.cmdOk(Seq("shasum", "-v")) then
          val out =
            Proc.cmdOutput(Seq("shasum", tempLocation.toString())).trim()
          val shasum :: _ = out.split("\\s+").toList.runtimeChecked

          assert(
            shasum == Files.contents(shaLocation),
            s"failed to download launcher jar: $jar (shasum mismatch)"
          )
        end if

        Files.move(tempLocation, downloadLocation)
        downloadLocation
      finally
        cleanup
          .result()
          .filter(Files.isFile(_))
          .foreach(Files.removeFile)
      end try

    end if
  end bootstrapImpl

  private def jarUrl(launcherVersion: String)(using Env): String =
    val repoBase = Env.variables
      .get("SBT_LAUNCH_REPO")
      .map(_.trim)
      .getOrElse("https://repo1.maven.org/maven2")

    s"$repoBase/org/scala-sbt/sbt-launch/$launcherVersion/sbt-launch-$launcherVersion.jar"

end BootstrapSbtJar
