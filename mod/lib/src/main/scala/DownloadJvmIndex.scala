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
      Env.get.userHome.resolve(s".cache/sabotage/jvm-index/$jvmIndex.json")

    if !Files.get.isFile(downloadLocation)
    then
      Files.get.createDirectories(downloadLocation.getParent())

      IMPROVE("Handle caching somehow")
      Log.info(
        s"Downloading JVM index [$jvmIndex] from [$url] into [$downloadLocation]"
      )
      Network.get.downloadFile(url, downloadLocation)

    val indexJson = Files.get.contents(downloadLocation)

    JvmIndex.read(indexJson)
  end acquireJvmIndex
end DownloadJvmIndex
