package sabotage.lib

import language.experimental.saferExceptions

object DownloadJvmIndex:
  def indexUrl(jvmIndex: String): String =
    jvmIndex.trim.toLowerCase() match
      case "coursier" =>
        "https://raw.githubusercontent.com/coursier/jvm-index/master/index.json"

  def acquireJvmIndex(
      jvmIndex: String
  )(using Network, Files, Logger, Env, CanThrow[Network.Err]): JvmIndex =
    val url = indexUrl(jvmIndex)
    val downloadLocation =
      Env.get.userHome.resolve(s".cache/sabotage/jvm-index/$jvmIndex.json")

    java.nio.file.Files.createDirectories(downloadLocation.getParent())
    if !Files.get.isFile(downloadLocation)
    then
      IMPROVE("Handle caching somehow")
      Logger.info(
        s"Downloading JVM index [$jvmIndex] from [$url] into [$downloadLocation]"
      )
      Network.get.downloadFile(url, downloadLocation)

    val location = Files.get.contents(downloadLocation)

    JvmIndex.read(location)
  end acquireJvmIndex
end DownloadJvmIndex
