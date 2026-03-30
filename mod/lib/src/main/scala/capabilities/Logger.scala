package sabotage.lib



trait Logger:
  def info(msg: String): Unit
  def warn(msg: String): Unit
  def error(msg: String): Unit

