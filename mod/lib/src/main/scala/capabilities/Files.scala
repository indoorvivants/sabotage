package sabotage.lib

import java.nio.file.Path

trait Files:
  def isDir(path: Path): Boolean
  def isFile(path: Path): Boolean
  def move(from: Path, to: Path): Unit
  def removeFile(path: Path): Unit
  def contents(path: Path): String
  def pwd: Path
  def resolve(path: String): Path
  def list(path: Path): List[Path]
  def createDirectories(path: Path): Unit
end Files

object Files extends CapabilityCompanion[Files]
