package sabotage.bin

import sabotage.lib.*
import language.experimental.saferExceptions
import java.nio.file.Path
import java.nio.file.Paths
import scala.util.Try
import scala.sys.process.ProcessLogger
import java.io.FileReader
import scala.util.Using
import sabotage.DownloadJvmIndex

/** TODO:
  *   - Fetch JAR
  *   - Fetch sbtn
  */

val logStderr = new Logger:
  override def info(msg: String) =
    System.err.println(msg)
  override def warn(msg: String) =
    System.err.println(msg)
  override def error(msg: String) =
    System.err.println(msg)

@main def hello =
  val env = new Env:
    override val variables = sys.env
    override def userHome: Path = _userHome

    private lazy val _userHome =
      Paths.get(variables("HOME")).toAbsolutePath()

    end _userHome

  given Context =
    Context(config = Config(), env = env, logger = logStderr)

  given Files with
    override def contents(path: Path): String =
      io.Source
        .fromFile(path.toFile())
        .getLines()
        .mkString(System.lineSeparator())

    override def isDir(path: Path): Boolean = path.toFile().isDirectory()
    override def isFile(path: Path): Boolean = path.toFile().isFile()
    override def move(from: Path, to: Path): Unit =
      java.nio.file.Files.move(from, to)
    override def removeFile(path: Path): Unit = java.nio.file.Files.delete(path)
    override def pwd: Path = Paths.get(System.getProperty("user.dir"))
    override def resolve(path: String): Path = Paths.get(path)
  end given

  given Proc with
    override def cmdOk(args: Seq[String]): Boolean =
      process(args*).exitCode == 0
    override def cmdOutput(args: Seq[String]): String =
      process(args*).stdout.mkString(System.lineSeparator())

  CurlNetwork.use:
    try
      val propertiesLocation = getFiles.pwd.resolve("project/build.properties")

      val properties =
        BuildProperties.read(getFiles.contents(propertiesLocation))

      val target = Platform.target

      val jarLocation = DownloadSbtJar.acquireSbtJar(properties.sbtVersion)

      val jdkHome = properties.jdk match
        case None => getFiles.resolve(getEnv.variables("JAVA_HOME"))
        case Some(jdkSpec) =>
          val index = DownloadJvmIndex.acquireJvmIndex(
            properties.jdkIndex.getOrElse("coursier")
          )

          val downloadUrl = properties
            .jdkUrl(jdkSpec, index, target)
            .getOrElse(sys.error(s"no JDK url found matching $jdkSpec"))

        // println(downloadUrl)

          println(DownloadJdk.download(downloadUrl))

        // DownloadJdk.

    catch
      case n: NetworkError => println(n.toString())
      case other           => throw other
end hello

def process(cmd: String*) =
  val stderr = List.newBuilder[String]
  val stdout = List.newBuilder[String]

  val logger = ProcessLogger.apply(
    (o: String) => stdout += o,
    (e: String) => stderr += e
  )

  val proces = new java.lang.ProcessBuilder(cmd*)
    .start()

  io.Source
    .fromInputStream(proces.getErrorStream())
    .getLines
    .foreach(logger.err(_))

  io.Source
    .fromInputStream(proces.getInputStream())
    .getLines
    .foreach(logger.out(_))

  val exitCode = proces.waitFor()

  ProcessResult(stdout.result(), stderr.result(), exitCode, cmd.toList)
end process

case class ProcessResult(
    stdout: List[String],
    stderr: List[String],
    exitCode: Int,
    command: List[String]
)
