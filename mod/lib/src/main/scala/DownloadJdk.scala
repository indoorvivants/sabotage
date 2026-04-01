package sabotage.lib

import java.io.FileInputStream
import java.nio.file.Path

import language.experimental.saferExceptions

object DownloadJdk:
  case class Err(msg: String, cause: Exception | Null = null)
      extends Exception(msg, cause)

  def download(
      rawUrl: String
  )(using
      Network,
      Files,
      Log,
      Env,
      Context,
      CanThrow[Err | Network.Err]
  ): Path =
    val (isTgz, downloadUrl) = rawUrl match
      case s"tgz+$rest" => true -> rest
      case _            => false -> rawUrl

    val downloadPath = downloadUrl.replaceFirst(":/", "")

    val archivePath =
      Env.get.userHome.resolve(s".cache/sabotage/jdk/$downloadPath.temp")

    val extractedPath =
      Env.get.userHome.resolve(s".cache/sabotage/jdk/$downloadPath")

    def extract() =
      val is =
        if isTgz then
          PatchedGZIPInputStream(FileInputStream(archivePath.toFile))
        else FileInputStream(archivePath.toFile)

      Log.info(s"Extracting $archivePath into $extractedPath")
      ExtractTar.extract(is, extractedPath)
      extractedPath

    if Files.get.isDir(extractedPath) then extractedPath
    else if Files.get.isFile(archivePath) then extract()
    else
      Log.info(s"Downloading jdk from [$downloadUrl] to [$archivePath]")
      java.nio.file.Files.createDirectories(archivePath.getParent())
      IMPROVE("Verify checksum of downloaded JDK")
      try
        Network.get.downloadFile(downloadUrl, archivePath)
        extract()
      finally
        if Files.get.isFile(archivePath) then Files.get.removeFile(archivePath)
    end if

    val failedToResolveHome = Err(
      s"Failed to to resolve the Java home location in [$extractedPath]"
    )

    def findHome(root: Path, depth: Int): Option[Path] throws Err =
      if depth <= 0 then throw failedToResolveHome
      else if root.resolve("Contents/Home/bin/java").toFile().isFile() then
        Some(root.resolve("Contents/Home"))
      else
        val children = Files.get.list(root)
        children.iterator
          .map(findHome(_, depth - 1))
          .collectFirst:
            case Some(p) => p

    import Platform.OS.*
    Context.get.platform.os match
      case MacOS =>
        findHome(extractedPath, 2).getOrElse(throw failedToResolveHome)
      case Linux => extractedPath
      case _     => extractedPath

  end download
end DownloadJdk
