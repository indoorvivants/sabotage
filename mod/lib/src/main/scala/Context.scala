package sabotage.lib

import java.nio.file.Path
import scala.util.Try

import language.experimental.saferExceptions

case class Context(config: Config, env: Env, logger: Logger)

case class Config()

inline def getConfig(using ctx: Context) = ctx.config
inline def getEnv(using ctx: Context) = ctx.env
inline def getLogger(using ctx: Context) = ctx.logger
inline def getNetwork(using ctx: Network) = ctx
inline def getProc(using ctx: Proc) = ctx
inline def getFiles(using ctx: Files) = ctx
