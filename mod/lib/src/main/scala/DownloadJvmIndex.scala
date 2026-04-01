package sabotage.lib

import language.experimental.saferExceptions

object DownloadJvmIndex:
  def indexUrl(jvmIndex: String): String =
    jvmIndex.trim.toLowerCase() match
      case "coursier" =>
        "https://raw.githubusercontent.com/coursier/jvm-index/master/index.json"
      case _ => TODO("unsupported JVM index")

  def acquireJvmIndex(
      jvmIndex: String
  )(using Network, Files, Log, Env, CanThrow[Network.Err]): JvmIndex =
    val url = indexUrl(jvmIndex)
    val downloadLocation =
      Env.userHome.resolve(s".cache/sabotage/jvm-index/$jvmIndex.json")

    if !Files.isFile(downloadLocation)
    then
      Files.createDirectories(downloadLocation.getParent())

      IMPROVE("Handle caching somehow")
      Log.info(
        s"Downloading JVM index [$jvmIndex] from [$url] into [$downloadLocation]"
      )
      Network.downloadFile(url, downloadLocation)

    val indexJson = Files.contents(downloadLocation)

    JvmIndex.read(indexJson)
  end acquireJvmIndex
end DownloadJvmIndex
