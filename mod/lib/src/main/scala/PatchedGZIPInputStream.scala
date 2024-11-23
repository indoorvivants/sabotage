package sabotage.lib

import java.util.zip.GZIPInputStream
import java.io.InputStream
import java.util.zip.InflaterInputStream

class PatchedGZIPInputStream(in: InputStream, size: Int)
    extends GZIPInputStream(in, size):

  def this(in: InputStream) = this(in, 512) //InflaterInputStream.BUF_SIZE)

  override def skip(value: Long): Long =
    if value < 0 then throw new IllegalArgumentException()

    var skipped = 0L
    val b = new Array[Byte](Math.min(value, 2048L).toInt)
    while skipped != value do
      val rem = value - skipped
      val x =
        read(b, 0, if b.length > rem then rem.toInt else b.length)
      if x == -1 then return skipped
      skipped += x
    skipped
  end skip
end PatchedGZIPInputStream
