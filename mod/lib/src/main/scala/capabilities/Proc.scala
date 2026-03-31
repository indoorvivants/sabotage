package sabotage.lib

trait Proc:
  def cmdOutput(args: Seq[String]): String
  def cmdOk(args: Seq[String]): Boolean

object Proc extends CapabilityCompanion[Proc]
