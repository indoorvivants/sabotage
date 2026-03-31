package sabotage.bin
import curl.*
import sabotage.lib.*

import java.nio.file.Path

import scalanative.unsafe.*
import scalanative.libc.*
import language.experimental.saferExceptions

class CurlNetwork private (inst: Ptr[CURL]) extends Network:
  override def downloadFile(url: String, path: Path)(using
      Log,
      CanThrow[Network.Err]
  ): Unit =
    Zone:
      Log.info(s"Downloading [$url] to [$path]")
      val cPath = toCString(path.toAbsolutePath().toString())
      val cUrl = toCString(url)
      check(curl_easy_setopt(inst, CURLoption.CURLOPT_URL, cUrl))
      check(curl_easy_setopt(inst, CURLoption.CURLOPT_FOLLOWLOCATION, 1L))

      val fp = stdio.fopen(cPath, c"wb")

      if fp == null then
        throw Network.Err(
          s"Failed to open path ${fromCString(cPath)} for writing"
        )

      val write_data_callback = CFuncPtr4.fromScalaFunction {
        (
            ptr: Ptr[Byte],
            size: CSize,
            nmemb: CSize,
            userdata: Ptr[stdio.FILE]
        ) =>
          stdio.fwrite(ptr, size, nmemb, userdata)
      }

      check(
        curl_easy_setopt(
          inst,
          CURLoption.CURLOPT_WRITEFUNCTION,
          write_data_callback
        )
      )

      check(curl_easy_setopt(inst, CURLoption.CURLOPT_WRITEDATA, fp))
      check(curl_easy_setopt(inst, CURLoption.CURLOPT_FAILONERROR, 1L))
      check(curl_easy_perform(inst))

      stdio.fclose(fp)

end CurlNetwork

object CurlNetwork:
  def use[A](f: Network ?=> A)(using CanThrow[Network.Err]) =
    val curl = curl_easy_init()
    if curl == null then throw Network.Err("Failed to initialise curl instance")
    try
      val inst = CurlNetwork(curl)
      f(using inst)
    finally curl_easy_cleanup(curl)
