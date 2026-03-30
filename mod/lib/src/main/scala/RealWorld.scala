package sabotage.lib

import java.nio.file.Path
import java.nio.file.Paths
import scala.jdk.CollectionConverters.ListHasAsScala

object RealWorld:
  def use(f: (Logger, Env, Files, Context, Proc) ?=> Unit): Unit =
    given logStderr: Logger = new Logger:
      override def info(msg: String) =
        System.err.println("[INF] " + msg)
      override def warn(msg: String) =
        System.err.println("[WRN] " + msg)
      override def error(msg: String) =
        System.err.println("[ERR] " + msg)

    given Env with
      override val variables = sys.env
      override def userHome: Path = _userHome

      private lazy val _userHome =
        Paths.get(variables("HOME")).toAbsolutePath()

      end _userHome

    given files: Files with
      override def contents(path: Path): String =
        io.Source
          .fromFile(path.toFile())
          .getLines()
          .mkString(System.lineSeparator())

      override def isDir(path: Path): Boolean = path.toFile().isDirectory()
      override def isFile(path: Path): Boolean = path.toFile().isFile()
      override def move(from: Path, to: Path): Unit =
        java.nio.file.Files.move(from, to)
      override def removeFile(path: Path): Unit =
        java.nio.file.Files.delete(path)
      override def pwd: Path = Paths.get(System.getProperty("user.dir"))
      override def resolve(path: String): Path = Paths.get(path)
      override def list(path: Path) =
        java.nio.file.Files.list(path).toList().asScala.toList
    end files

    given Proc with
      override def cmdOk(args: Seq[String]): Boolean =
        process(args*).exitCode == 0
      override def cmdOutput(args: Seq[String]): String =
        process(args*).stdout.mkString(System.lineSeparator())

    given context: Context =
      Context(
        platform = Platform.target
      )

    f
  end use
end RealWorld
