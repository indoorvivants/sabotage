package sabotage.lib

trait Proc:
  def cmdOutput(args: Seq[String]): String
  def cmdOk(args: Seq[String]): Boolean
  def execve(
      command: String,
      env: Map[String, String],
      arguments: Seq[String]
  ): Option[String]

object Proc extends CapabilityCompanion[Proc]:
  inline def execve(
      command: String,
      env: Map[String, String],
      arguments: Seq[String]
  )(using p: Proc): Option[String] = p.execve(command, env, arguments)
  inline def cmdOutput(args: Seq[String])(using p: Proc) = p.cmdOutput(args)
  inline def cmdOk(args: Seq[String])(using p: Proc) = p.cmdOk(args)
