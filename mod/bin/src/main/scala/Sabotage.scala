package sabotage.bin

import sabotage.lib.*
import language.experimental.saferExceptions

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

  given Context =
    Context(config = Config(), env = env, logger = logStderr)

  CurlNetwork.use:
    try println(DownloadSbtJar.acquireSbtJar("1.7.0"))
    catch case n: NetworkError => println(n)
end hello
