package sabotage.lib

import java.io.*
import java.nio.file.*
import scala.util.boundary
import language.experimental.saferExceptions

object ExtractTar:
  def extract(tarStream: InputStream, out: Path)(using Context) =

    def transferN(is: InputStream, n: Int, os: OutputStream) =
      val buffer = Array.ofDim[Byte](1024)
      var read = -1
      var transferred = 0
      while {
        read = is.read(buffer, 0, (n - transferred).min(1024)); read > 0
      }
      do
        os.write(buffer, 0, read)
        transferred += read

      transferred
    end transferN

    boundary:
      var fos = Option.empty[FileOutputStream]
      def readObject() =
        var read = 0
        def ascii(n: Int) =
          read += n
          val bytes = tarStream.readNBytes(n)
          if bytes.length != n then boundary.break()
          else new String(bytes.takeWhile(_ != 0)).trim()
        val name = ascii(100)
        if name == "" then boundary.break()
        val mode = ascii(8)
        val ownerId = ascii(8)
        val groupId = ascii(8)
        val sizeStr = ascii(12)
        val mtime = ascii(12)
        val checksum = ascii(8)
        val linkOrType = ascii(1)
        val linkedFileName = ascii(100)
        val ustarIndicator = ascii(6)
        val headerRemaining = 512 - read
        val isDir = linkOrType == "5"

        val size = Integer.parseInt(sizeStr, 8)

        val destination = out.resolve(name)

        val skipped = tarStream.skip(headerRemaining)

        val padding = if size % 512 == 0 then 0 else (512 - (size % 512))

        if isDir then
          if !Files.isDirectory(destination) then
            Files.createDirectories(destination)
        else
          val fos = new FileOutputStream(destination.toFile())
          transferN(tarStream, size, fos)
          fos.close()

          tarStream.skip(padding)

        getLogger.info(s"Extracted [$name]")
      end readObject

      while true do readObject()
  end extract
end ExtractTar
