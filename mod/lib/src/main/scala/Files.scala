package sabotage.lib

import java.nio.file.Path
import scala.util.Try
import language.experimental.saferExceptions

trait Files:
  def isDir(path: Path): Boolean
  def isFile(path: Path): Boolean
  def move(from: Path, to: Path): Unit
  def removeFile(path: Path): Unit
  def contents(path: Path): String
  def pwd: Path
  def resolve(path: String): Path
