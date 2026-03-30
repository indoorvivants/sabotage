package sabotage.lib



case class Defaults(sbtVersion: String, sbtnVersion: String)

case class Context(
    platform: sabotage.lib.Platform.Target,
    defaults: Defaults
)

inline def getDefaults(using ctx: Context) = ctx.defaults
inline def getEnv(using ctx: Env) = ctx
inline def getLogger(using ctx: Logger) = ctx
inline def getNetwork(using ctx: Network) = ctx
inline def getProc(using ctx: Proc) = ctx
inline def getFiles(using ctx: Files) = ctx
inline def getPlatform(using ctx: Context) = ctx.platform
