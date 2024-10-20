package sabotage.lib

import java.nio.file.Path
import scala.util.Try

trait Env:
  def variables: Map[String, String]
  def userHome: Path
