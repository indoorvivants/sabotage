package sabotage.lib

import java.nio.file.Path

trait Files extends caps.SharedCapability:
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

object Files extends CapabilityCompanion[Files]:
  inline def isDir(path: Path)(using Files) = get.isDir(path)
  inline def isFile(path: Path)(using Files) = get.isFile(path)
  inline def removeFile(path: Path)(using Files) = get.removeFile(path)
  inline def move(from: Path, to: Path)(using Files) = get.move(from, to)
  inline def createDirectories(path: Path)(using Files) =
    get.createDirectories(path)
  inline def contents(path: Path)(using Files) = get.contents(path)
  inline def pwd(using Files) = get.pwd
  inline def resolve(path: String)(using Files) = get.resolve(path)
  inline def list(path: Path)(using Files) = get.list(path)
end Files
