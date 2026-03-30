package sabotage.lib

import java.nio.file.Path
import language.experimental.saferExceptions
import java.util.zip.GZIPInputStream
import java.io.FileInputStream

object DownloadJdk:
  case class Err(msg: String, cause: Exception | Null = null)
      extends Exception(msg, cause)

  def download(
      rawUrl: String
  )(using
      Network,
      Files,
      Logger,
      Env,
      Context
  ): Path throws (NetworkError | Err) =
    val (isTgz, downloadUrl) = rawUrl match
      case s"tgz+$rest" => true -> rest
      case _            => false -> rawUrl

    val downloadPath = downloadUrl.replaceFirst(":/", "")

    val archivePath =
      getEnv.userHome.resolve(s".cache/sabotage/jdk/$downloadPath.temp")

    val extractedPath =
      getEnv.userHome.resolve(s".cache/sabotage/jdk/$downloadPath")

    def extract() =
      val is =
        if isTgz then
          PatchedGZIPInputStream(FileInputStream(archivePath.toFile))
        else FileInputStream(archivePath.toFile)

      getLogger.info(s"Extracting $archivePath into $extractedPath")
      ExtractTar.extract(is, extractedPath)
      extractedPath

    if getFiles.isDir(extractedPath) then extractedPath
    else if getFiles.isFile(archivePath) then extract()
    else
      getLogger.info(s"Downloading jdk from [$downloadUrl] to [$archivePath]")
      java.nio.file.Files.createDirectories(archivePath.getParent())
      IMPROVE("Verify checksum of downloade JDK")
      getNetwork.downloadFile(downloadUrl, archivePath)
      extract()
    end if

    val failedToResolveHome = Err(
      s"Failed to to resolve the Java home location in [$extractedPath]"
    )

    def findHome(root: Path, depth: Int): Option[Path] throws Err =
      if depth <= 0 then throw failedToResolveHome
      else if root.resolve("Contents/Home/bin/java").toFile().isFile() then
        Some(root.resolve("Contents/Home"))
      else
        val children = getFiles.list(root)
        children.iterator
          .map(findHome(_, depth - 1))
          .collectFirst:
            case Some(p) => p

    import Platform.OS.*
    getPlatform.os match
      case MacOS =>
        findHome(extractedPath, 2).getOrElse(throw failedToResolveHome)
      case Linux => extractedPath
      case _     => extractedPath

  end download
end DownloadJdk
