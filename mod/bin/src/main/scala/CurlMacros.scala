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
    assert(
      code == CURLcode.CURLE_OK,
      $e + "[" + fromCString(curl_easy_strerror(code)) + "]"
    )
    code
  }
end checkImpl
