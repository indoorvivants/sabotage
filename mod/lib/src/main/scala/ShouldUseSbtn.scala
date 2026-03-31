package sabotage.lib

import math.Ordering.Implicits.*

object ShouldUseSbtn:
  def decide(sbtVersion: Version, args: LauncherArgs)(using Env): Boolean =
    val clientDisabledViaArgs = args.jvmClient || args.server
    if Env.variables.get("SBT_NATIVE_CLIENT").contains("true") then true
    else if sbtVersion >= Versions.sbt2 then !clientDisabledViaArgs
    else if sbtVersion >= Versions.minimumVersionForSbtn then
      !clientDisabledViaArgs && args.client
    else false
