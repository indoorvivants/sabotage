package sabotage.lib

import scala.quoted.*

/** This macro produces a compiler warning at use site AND throws at runtime if
  * the code is ever reached
  *
  * @param msg
  * @return
  */
transparent inline def TODO(inline msg: String) =
  ${ todoImpl('msg) }

private class You_fucked_around_and_found_out(msg: String)
    extends RuntimeException(msg)

private def todoImpl(msg: Expr[String])(using Quotes): Expr[Any] =
  val value = msg.valueOrAbort

  quotes.reflect.report.warning(s"TODO: $value")

  '{ throw new You_fucked_around_and_found_out($msg) }
end todoImpl
