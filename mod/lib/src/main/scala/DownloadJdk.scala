package sabotage.lib

import java.nio.file.Path
import language.experimental.saferExceptions
import java.util.zip.GZIPInputStream
import java.io.FileInputStream

object DownloadJdk:
  def download(
      rawUrl: String
  )(using Network, Files, Context): Path throws NetworkError =
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

      ExtractTar.extract(is, extractedPath)
      extractedPath

    if getFiles.isDir(extractedPath) then extractedPath
    else if getFiles.isFile(archivePath) then extract()
    else
      getLogger.info(s"Downloading jdk from [$downloadUrl] to [$archivePath]")
      java.nio.file.Files.createDirectories(archivePath.getParent())
      // TODO: verify checksum somehow?
      getNetwork.downloadFile(downloadUrl, archivePath)
      extract()
    end if

    extractedPath

  end download
end DownloadJdk
