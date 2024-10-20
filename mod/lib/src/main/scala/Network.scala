package sabotage.lib

import java.nio.file.Path
import scala.util.Try

import language.experimental.saferExceptions

case class NetworkError(msg: String) extends Exception
trait Network:
  def downloadFile(url: String, path: Path): Unit throws NetworkError

