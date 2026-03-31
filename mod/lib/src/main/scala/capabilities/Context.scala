package sabotage.lib

case class Context(
    platform: sabotage.lib.Platform.Target,
    defaults: Defaults
)

object Context extends CapabilityCompanion[Context]:
  inline def defaults(using ctx: Context) = ctx.defaults
  inline def platform(using ctx: Context) = ctx.platform
