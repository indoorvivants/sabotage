package sabotage.lib

import java.nio.file.Path
import scala.util.Try

case class Context(config: Config, env: Env, logger: Logger)

trait Env:
  def variables: Map[String, String]
  def userHome: Path


case class Config()

inline def getConfig(using ctx: Context) = ctx.config
inline def getEnv(using ctx: Context) = ctx.env
inline def getLogger(using ctx: Context) = ctx.logger
inline def getNetwork(using ctx: Network) = ctx
inline def getProc(using ctx: Proc) = ctx
inline def getFiles(using ctx: Files) = ctx

trait Logger:
  def info(msg: String): Unit
  def warn(msg: String): Unit
  def error(msg: String): Unit

import language.experimental.saferExceptions

case class NetworkError(msg: String) extends Exception

trait Network:
  def downloadFile(url: String, path: Path): Unit throws NetworkError

trait Proc:
  def cmdOutput(args: Seq[String]): String
  def cmdOk(args: Seq[String]): Boolean

trait Files:
  def isDir(path: Path): Boolean
  def isFile(path: Path): Boolean
  def move(from: Path, to: Path): Unit
  def removeFile(path: Path): Unit
  def contents(path: Path): String
