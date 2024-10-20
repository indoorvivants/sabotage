package sabotage.lib

import java.nio.file.Path
import scala.util.Try
import language.experimental.saferExceptions

trait Proc:
  def cmdOutput(args: Seq[String]): String
  def cmdOk(args: Seq[String]): Boolean

