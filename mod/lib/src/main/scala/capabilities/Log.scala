package sabotage.lib

trait Log extends caps.SharedCapability:
  def info(msg: String): Unit
  def warn(msg: String): Unit
  def error(msg: String): Unit

object Log extends CapabilityCompanion[Log]:
  inline def info(msg: String)(using Log): Unit = get.info(msg)
  inline def warn(msg: String)(using Log): Unit = get.warn(msg)
  inline def error(msg: String)(using Log): Unit = get.error(msg)
