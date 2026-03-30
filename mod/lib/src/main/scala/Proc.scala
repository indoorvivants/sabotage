package sabotage.lib

import scala.sys.process.ProcessLogger

trait Proc:
  def cmdOutput(args: Seq[String]): String
  def cmdOk(args: Seq[String]): Boolean

def process(cmd: String*) =
  val stderr = List.newBuilder[String]
  val stdout = List.newBuilder[String]

  val logger = ProcessLogger.apply(
    (o: String) => stdout += o,
    (e: String) => stderr += e
  )

  val proces = new java.lang.ProcessBuilder(cmd*)
    .start()

  io.Source
    .fromInputStream(proces.getErrorStream())
    .getLines
    .foreach(logger.err(_))

  io.Source
    .fromInputStream(proces.getInputStream())
    .getLines
    .foreach(logger.out(_))

  val exitCode = proces.waitFor()

  ProcessResult(stdout.result(), stderr.result(), exitCode, cmd.toList)
end process

case class ProcessResult(
    stdout: List[String],
    stderr: List[String],
    exitCode: Int,
    command: List[String]
)
