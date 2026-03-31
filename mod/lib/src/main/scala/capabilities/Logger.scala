package sabotage.lib

trait Logger extends caps.SharedCapability:
  def info(msg: String): Unit
  def warn(msg: String): Unit
  def error(msg: String): Unit

object Logger extends CapabilityCompanion[Logger]:
  inline def info(msg: String)(using Logger): Unit = get.info(msg)
  inline def warn(msg: String)(using Logger): Unit = get.warn(msg)
  inline def error(msg: String)(using Logger): Unit = get.error(msg)
