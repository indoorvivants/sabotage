package sabotage.bin

import curl.{CURLcode, curl_easy_strerror}

import scala.quoted.*

import scalanative.unsafe.fromCString

inline def check(inline expr: => CURLcode): CURLcode = ${ checkImpl('expr) }

private def checkImpl(expr: Expr[CURLcode])(using Quotes): Expr[CURLcode] =
  import quotes.*
  val e = Expr(s"${expr.show} failed: ")

  '{
    val code = $expr
    if code != CURLcode.CURLE_OK then
      throw sabotage.lib.Network.Err(
        $e + "[" + fromCString(curl_easy_strerror(code)) + "]"
      )

    code
  }
end checkImpl
