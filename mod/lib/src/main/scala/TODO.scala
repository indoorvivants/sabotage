package sabotage.lib

import scala.quoted.*

/** Use this macro to mark paths that need to produce a meaningful error when
  * reached. This macro will produce a compiler warning, and will crash with
  * exception if ever reached.
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

/** Use this macro to emit a compiler warning with a meaningful message (it does
  * nothing at runtime)
  *
  * @param msg
  * @return
  */
transparent inline def IMPROVE(inline msg: String) =
  ${ improveImpl('msg) }

private def improveImpl(msg: Expr[String])(using Quotes): Expr[Any] =
  val value = msg.valueOrAbort

  quotes.reflect.report.warning(s"TODO: $value")

  '{ () }
