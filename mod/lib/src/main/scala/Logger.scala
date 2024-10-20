package sabotage.lib

import java.nio.file.Path
import scala.util.Try

trait Logger:
  def info(msg: String): Unit
  def warn(msg: String): Unit
  def error(msg: String): Unit

