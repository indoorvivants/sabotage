package sabotage.lib

import sabotage.lib.Platform.*

import java.io.FileInputStream
import java.nio.file.{Path, Paths}

import language.experimental.saferExceptions

object BootstrapSbtn:
  case class Err(msg: String, cause: Throwable | Null = null)
      extends Exception(msg, cause)

  case class SbtnUrl(
      name: "sbtn" | "sbtn.exe",
      archive: "tar.gz" | "zip",
      url: String
  )

  def archiveUrl(
      launcherVersion: String
  )(using Context, CanThrow[Err]): SbtnUrl =
    def url(qualifier: String, ext: String) =
      s"https://github.com/sbt/sbtn-dist/releases/download/v$launcherVersion/sbtn-$qualifier-${launcherVersion}.$ext"

    Context.platform match
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
      Env,
      CanThrow[Err | Network.Err]
  ): Path =
    val archive = archiveUrl(sbtnVersion)

    val downloadLocation =
      Env.get.userHome.resolve(
        s".cache/sbt/boot/sbtn/$sbtnVersion/${archive.name}"
      )

    Logger.info(s"sbtn location $downloadLocation")

    if Files.get.isFile(downloadLocation) then downloadLocation
    else
      val tempLocation = Paths.get(downloadLocation.toString + ".temp")
      Logger.info(
        s"downloading sbtn $sbtnVersion from [${archive.url}] to [$tempLocation]"
      )
      val extractedPath = downloadLocation.getParent()

      Files.get.createDirectories(extractedPath)

      Network.get.downloadFile(archive.url, tempLocation)

      archive.archive match
        case "tar.gz" =>
          Logger.info(s"Extracting $tempLocation into $extractedPath")
          ExtractTar.extract(
            PatchedGZIPInputStream(FileInputStream(tempLocation.toFile)),
            extractedPath
          )
        case "zip" => TODO("handle zip archives (windows)")

      downloadLocation
    end if
  end bootstrap
end BootstrapSbtn
