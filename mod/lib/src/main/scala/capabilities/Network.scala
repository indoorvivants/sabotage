package sabotage.lib

import java.nio.file.Path

import language.experimental.saferExceptions

trait Network extends caps.SharedCapability:
  def downloadFile(url: String, path: Path)(using
      Log,
      CanThrow[Network.Err]
  ): Unit

object Network extends CapabilityCompanion[Network]:
  case class Err(msg: String, cause: Throwable | Null = null)
      extends Exception(msg, cause)

  inline def downloadFile(url: String, path: Path)(using
      Network,
      Log,
      CanThrow[Network.Err]
  ): Unit = get.downloadFile(url, path)
