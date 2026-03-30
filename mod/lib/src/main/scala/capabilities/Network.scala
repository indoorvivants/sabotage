package sabotage.lib

import java.nio.file.Path

import language.experimental.saferExceptions

case class NetworkError(msg: String) extends Exception
trait Network:
  def downloadFile(url: String, path: Path)(using Logger): Unit throws NetworkError

