package sabotage

import sabotage.lib.*
import language.experimental.saferExceptions

object DownloadJvmIndex:
  def indexUrl(jvmIndex: String): String =
    jvmIndex.trim.toLowerCase() match
      case "coursier" =>
        "https://raw.githubusercontent.com/coursier/jvm-index/master/index.json"

  def acquireJvmIndex(
      jvmIndex: String
  )(using Network, Files, Context): JvmIndex throws NetworkError =
    val url = indexUrl(jvmIndex)
    val downloadLocation =
      getEnv.userHome.resolve(s".cache/sabotage/jvm-index/$jvmIndex.json")

    java.nio.file.Files.createDirectories(downloadLocation.getParent())
    if !getFiles.isFile(downloadLocation)
    then // TODO: cache refresh? force no cache?
      getLogger.info(
        s"Downloading JVM index [$jvmIndex] from [$url] into [$downloadLocation]"
      )
      getNetwork.downloadFile(url, downloadLocation)

    val location = getFiles.contents(downloadLocation)

    JvmIndex.read(location)
  end acquireJvmIndex
end DownloadJvmIndex
