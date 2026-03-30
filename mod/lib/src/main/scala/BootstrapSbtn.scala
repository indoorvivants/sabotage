package sabotage.lib

import sabotage.lib.Platform.*

import java.nio.file.Path

import language.experimental.saferExceptions
import java.nio.file.Paths
import java.io.FileInputStream

object BootstrapSbtn:
  case class Err(msg: String, cause: Throwable | Null = null)
      extends Exception(msg, cause)

  case class SbtnUrl(
      name: "sbtn" | "sbtn.exe",
      archive: "tar.gz" | "zip",
      url: String
  )

  def archiveUrl(launcherVersion: String)(using Context): SbtnUrl throws Err =
    def url(qualifier: String, ext: String) =
      s"https://github.com/sbt/sbtn-dist/releases/download/v$launcherVersion/sbtn-$qualifier-${launcherVersion}.$ext"

    getPlatform match
      case Target(OS.Linux, Arch.Intel, Bits.x64) =>
        SbtnUrl("sbtn", "tar.gz", url("x86_64-pc-linux", "tar.gz"))
      case Target(OS.Linux, Arch.Arm, Bits.x64) =>
        SbtnUrl("sbtn", "tar.gz", url("aarch64-pc-linux", "tar.gz"))
      case Target(OS.MacOS, _, Bits.x64) =>
        SbtnUrl("sbtn", "tar.gz", url("universal-apple-darwin", "tar.gz"))
      case Target(OS.Windows, Arch.Intel, Bits.x64) =>
        SbtnUrl("sbtn.exe", "zip", url("x86_64-pc-win32", "zip"))
      case other =>
        throw Err(s"sbtn is not available for target [$other]")
    end match
  end archiveUrl

  def bootstrap(
      sbtnVersion: String
  )(using
      Context,
      Network,
      Files,
      Logger,
      Env
  ): Path throws (NetworkError | Err) =
    val archive = archiveUrl(sbtnVersion)

    val downloadLocation =
      getEnv.userHome.resolve(
        s".cache/sbt/boot/sbtn/$sbtnVersion/${archive.name}"
      )

    getLogger.info(s"sbtn location $downloadLocation")

    if getFiles.isFile(downloadLocation) then downloadLocation
    else
      val tempLocation = Paths.get(downloadLocation.toString + ".temp")
      getLogger.info(
        s"downloading sbtn $sbtnVersion from [${archive.url}] to [$tempLocation]"
      )
      val extractedPath = downloadLocation.getParent()

      getFiles.createDirectories(extractedPath)

      getNetwork.downloadFile(archive.url, tempLocation)

      IMPROVE("Handle zip archives")
      archive.archive match
        case "tar.gz" =>
          getLogger.info(s"Extracting $tempLocation into $extractedPath")
          ExtractTar.extract(
            PatchedGZIPInputStream(FileInputStream(tempLocation.toFile)),
            extractedPath
          )

      downloadLocation
    end if
  end bootstrap
end BootstrapSbtn
