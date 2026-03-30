package sabotage.lib

case class LauncherArgs(
    help: Boolean,
    printVersion: Boolean,
    verbose: Boolean,
    client: Boolean,
    server: Boolean,
    jvmClient: Boolean,
    debug: Boolean,
    pass: Seq[String],
    javaOpts: Seq[String],
    sbtOpts: Seq[String]
):
  override def toString(): String =
    s"""
    |LauncherArgs:
    |-help: $help
    |-printVersion: $printVersion
    |-verbose: $verbose
    |-client: $client
    |-server: $server
    |-jvmClient: $jvmClient
    |-debug: $debug
    |-pass: $pass
    |-javaOpts: $javaOpts
    |-sbtOpts: $sbtOpts
    """.trim.stripMargin
end LauncherArgs

object LauncherArgs:
  val default = LauncherArgs(
    help = false,
    printVersion = false,
    verbose = false,
    client = false,
    server = false,
    jvmClient = false,
    debug = false,
    pass = Seq.empty,
    javaOpts = Seq.empty,
    sbtOpts = Seq.empty
  )
end LauncherArgs
