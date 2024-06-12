package sabotage.lib

import java.nio.file.Paths
import java.nio.file.Path

import language.experimental.saferExceptions

object DownloadSbtJar:
  def jarUrl(launcherVersion: String)(using Context): String =
    val repoBase = getEnv.variables
      .get("SBT_LAUNCH_REPO")
      .map(_.trim)
      .getOrElse("https://repo1.maven.org/maven2")

    s"$repoBase/org/scala-sbt/sbt-launch/$launcherVersion/sbt-launch-$launcherVersion.jar"

  def acquireSbtJar(
      launcherVersion: String
  )(using Context, Network, Files, Proc): Path throws (NetworkError) =
    val jar = jarUrl(launcherVersion)
    val sha = jar + ".sha1"
    val downloadLocation =
      getEnv.userHome.resolve(
        s".cache/sbt/boot/sbt-launch/$launcherVersion/sbt-launch-$launcherVersion.jar"
      )

    java.nio.file.Files.createDirectories(downloadLocation.getParent())

    if getFiles.isFile(downloadLocation) then downloadLocation
    else
      val shaLocation = Paths.get(downloadLocation.toString + ".sha1")
      val tempLocation = Paths.get(downloadLocation.toString + ".temp")
      getLogger.info(s"downloading sbt launcher $launcherVersion")
      getLogger.info(jar.toString)
      getLogger.info(tempLocation.toString)
      getNetwork.downloadFile(jar, tempLocation)
      getNetwork.downloadFile(sha, shaLocation)

      if getProc.cmdOk(Seq("shasum", "-v")) then
        val out =
          getProc.cmdOutput(Seq("shasum", tempLocation.toString())).trim()
        val shasum :: _ = out.split("\\s+").toList

        assert(
          shasum == getFiles.contents(shaLocation),
          s"failed to download launcher jar: $jar (shasum mismatch)"
        )
      end if

      getFiles.move(tempLocation, downloadLocation)

      getFiles.removeFile(tempLocation)
      getFiles.removeFile(shaLocation)

      downloadLocation
    end if
  end acquireSbtJar
end DownloadSbtJar
