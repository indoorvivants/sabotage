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

  def acquireSbtJar(launcherVersion: String)(using Context, Network): Path throws (NetworkError) =
    val jar = jarUrl(launcherVersion)
    val sha = jar + ".sha1"
    getNetwork.downloadFile(
      sha,
      Paths.get("./sbt-launch-test.jar.sha1")
    )
    ???
end DownloadSbtJar
