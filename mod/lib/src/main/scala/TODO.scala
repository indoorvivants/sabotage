package sabotage.lib

import scala.quoted.*

transparent inline def TODO(inline msg: String) =
  ${ todoImpl('msg) }

private class You_fucked_around_and_found_out(msg: String)
    extends RuntimeException(msg)

private def todoImpl(msg: Expr[String])(using Quotes): Expr[Any] =
  val value = msg.valueOrAbort

  quotes.reflect.report.warning(s"TODO: $value")

  '{ throw new You_fucked_around_and_found_out($msg) }
end todoImpl

transparent inline def IMPROVE(inline msg: String) =
  ${ improveImpl('msg) }

private def improveImpl(msg: Expr[String])(using Quotes): Expr[Any] =
  val value = msg.valueOrAbort

  quotes.reflect.report.warning(s"TODO: $value")

  '{ () }
