package sabotage.lib

import java.nio.file.Path

trait Env extends caps.SharedCapability:
  def variables: Map[String, String]
  def userHome: Path

object Env extends CapabilityCompanion[Env]:
  inline def variables(using env: Env) = env.variables
  inline def userHome(using env: Env) = env.userHome
