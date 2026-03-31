package sabotage.lib

import java.nio.file.{Path, Paths}

import language.experimental.saferExceptions

object BootstrapSbtJar:
  def bootstrap(
      launcherVersion: String
  )(using
      Network,
      Files,
      Proc,
      Logger,
      Env
  ): Path throws (Network.Err) =
    val jar = jarUrl(launcherVersion)
    val sha = jar + ".sha1"
    val downloadLocation =
      Env.get.userHome.resolve(
        s".cache/sbt/boot/sbt-launch/$launcherVersion/sbt-launch-$launcherVersion.jar"
      )

    Files.get.createDirectories(downloadLocation.getParent())

    if Files.get.isFile(downloadLocation) then downloadLocation
    else
      val shaLocation = Paths.get(downloadLocation.toString + ".sha1")
      val tempLocation = Paths.get(downloadLocation.toString + ".temp")
      Logger.info(
        s"downloading sbt launcher $launcherVersion from [$jar] to [$tempLocation]"
      )
      Network.get.downloadFile(jar, tempLocation)
      Network.get.downloadFile(sha, shaLocation)

      IMPROVE("Avoid shelling out to shasum")
      if Proc.get.cmdOk(Seq("shasum", "-v")) then
        val out =
          Proc.get.cmdOutput(Seq("shasum", tempLocation.toString())).trim()
        val shasum :: _ = out.split("\\s+").toList.runtimeChecked

        assert(
          shasum == Files.get.contents(shaLocation),
          s"failed to download launcher jar: $jar (shasum mismatch)"
        )
      end if

      Files.get.move(tempLocation, downloadLocation)
      Files.get.removeFile(shaLocation)

      downloadLocation
    end if
  end bootstrap

  private def jarUrl(launcherVersion: String)(using Env): String =
    val repoBase = Env.get.variables
      .get("SBT_LAUNCH_REPO")
      .map(_.trim)
      .getOrElse("https://repo1.maven.org/maven2")

    s"$repoBase/org/scala-sbt/sbt-launch/$launcherVersion/sbt-launch-$launcherVersion.jar"

end BootstrapSbtJar
