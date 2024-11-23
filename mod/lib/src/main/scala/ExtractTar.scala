package sabotage.lib

import java.io.*
import java.nio.file.*
import scala.util.boundary
import language.experimental.saferExceptions

object ExtractTar:
  enum Result:
    case Ok
    case Err(msg: String)

  def extract(tarStream: InputStream, out: Path)(using Context): Result =

    def transferN(is: InputStream, n: Int, outputStream: OutputStream) =
      val buffer = Array.ofDim[Byte](1024)
      var read = -1
      var transferred = 0
      while {
        read = is.read(buffer, 0, (n - transferred).min(1024)); read > 0
      }
      do
        outputStream.write(buffer, 0, read)
        transferred += read

      transferred
    end transferN

    var fos = Option.empty[FileOutputStream]
    boundary:
      def readN(in: InputStream, n: Int) =
        val bytes = new Array[Byte](n)
        var actually = in.read(bytes, 0, n)
        while actually < n do
          val read = in.read(bytes, actually, n - actually)
          if read == -1 then
            boundary.break(Result.Err("end of stream unexpectedly"))
          else actually += read
        bytes

      def readObject(): Unit =
        var read = 0
        inline def ascii(n: Int, label: String = "") =
          read += n
          val bytes = readN(tarStream, n)
          val res = new String(bytes.takeWhile(_ != 0)).trim()
          // println(s"$label: $n --  ${bytes.toList} -- $res")
          res
        end ascii

        val name = ascii(100, "name")
        if name == "" then boundary.break(Result.Ok)
        val mode = ascii(8, "mode")
        val ownerId = ascii(8, "ownerId")
        val groupId = ascii(8, "groupId")
        val sizeStr = ascii(12, "size")
        val mtime = ascii(12, "mtime")
        val checksum = ascii(8, "checksum")
        val linkOrType = ascii(1, "linkOrType")
        val linkedFileName = ascii(100, "linkedFileName")
        val ustarIndicator = ascii(6, "ustarIndicator")
        val headerRemaining = 512 - read
        val isDir = linkOrType == "5"

        val size = Integer.parseInt(sizeStr, 8)

        val destination = out.resolve(name)

        val skipped = tarStream.skip(headerRemaining)

        val padding = if size % 512 == 0 then 0 else (512 - (size % 512))

        // println(s"Size: $size, padding: $padding")

        if isDir then
          if !Files.isDirectory(destination) then
            Files.createDirectories(destination)
        else
          val fos = new FileOutputStream(destination.toFile())
          transferN(tarStream, size, fos)
          fos.close()

          chmod(destination, Integer.parseInt(mode))

          tarStream.skip(padding)
        end if

      end readObject

      while true do readObject()

      Result.Ok
  end extract

  private def chmod(path: Path, perms: Int) =
    import scalanative.posix.sys.stat.chmod as libc_chmod
    import scalanative.posix.sys.types.mode_t
    import scalanative.*, unsigned.*, unsafe.*

    val p: mode_t = perms.toUInt
    Zone:
      libc_chmod(toCString(path.toString()), p)
end ExtractTar
