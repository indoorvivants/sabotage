package sabotage.lib

import java.nio.file.Path

import language.experimental.saferExceptions

trait Network:
  def downloadFile(url: String, path: Path)(using
      Logger
  ): Unit throws Network.Err

object Network extends CapabilityCompanion[Network]:
  case class Err(msg: String, cause: Throwable | Null = null)
      extends Exception(msg, cause)
