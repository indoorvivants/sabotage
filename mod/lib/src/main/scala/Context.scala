package sabotage.lib

import java.nio.file.Path
import scala.util.Try

case class Context(config: Config, env: Env, logger: Logger)

trait Env:
  val variables: Map[String, String]

case class Config()

inline def getConfig(using ctx: Context) = ctx.config
inline def getEnv(using ctx: Context) = ctx.env
inline def getNetwork(using ctx: Network) = ctx
inline def getProc(using ctx: Proc) = ctx

trait Logger:
  def info(msg: String): Unit
  def warn(msg: String): Unit
  def error(msg: String): Unit

import language.experimental.saferExceptions

case class NetworkError(msg: String) extends Exception
trait Network:
  def downloadFile(url: String, path: Path): Unit throws NetworkError

trait Proc:
  def cmdOutput(args: Seq[String]): Try[String]
