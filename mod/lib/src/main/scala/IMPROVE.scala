package sabotage.lib

import scala.quoted.*

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
