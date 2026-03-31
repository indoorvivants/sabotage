package sabotage.lib

import java.nio.file.Path

case class LauncherArgs(
    help: Boolean,
    printVersion: Boolean,
    verbose: Boolean,
    client: Boolean,
    server: Boolean,
    jvmClient: Boolean,
    debug: Boolean,
    noColors: Boolean,
    timings: Boolean,
    traces: Boolean,
    color: Option[String],
    noShare: Boolean,
    noGlobal: Boolean,
    allowEmpty: Boolean,
    newCmd: Boolean,
    shutdownallCmd: Boolean,
    bootDir: Option[Path],
    globalDir: Option[Path],
    sbtJar: Option[Path],
    sbtCache: Option[Path],
    javaHome: Option[Path],
    mem: Option[Int],
    pass: Seq[String],
    sbtVersion: Option[String],
    javaOpts: Seq[String],
    sbtOpts: Seq[String]
):
  override def toString(): String =
    s"""
    |LauncherArgs:
    | - help: $help
    | - printVersion: $printVersion
    | - verbose: $verbose
    | - client: $client
    | - server: $server
    | - jvmClient: $jvmClient
    | - debug: $debug
    | - pass: $pass
    | - javaOpts: $javaOpts
    | - sbtOpts: $sbtOpts
    | - noColors: $noColors
    | - timings: $timings
    | - traces: $traces
    | - color: $color
    | - noShare: $noShare
    | - noGlobal: $noGlobal
    | - allowEmpty: $allowEmpty
    | - newCmd: $newCmd
    | - mem: $mem
    | - sbtVersion: $sbtVersion
    | - shutdownallCmd: $shutdownallCmd
    | - javaHome: $javaHome
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
    noColors = false,
    timings = false,
    traces = false,
    color = None,
    noShare = false,
    noGlobal = false,
    allowEmpty = false,
    newCmd = false,
    shutdownallCmd = false,
    bootDir = None,
    globalDir = None,
    sbtJar = None,
    sbtCache = None,
    javaHome = None,
    mem = None,
    sbtVersion = None,
    pass = Seq.empty,
    javaOpts = Seq.empty,
    sbtOpts = Seq.empty
  )
end LauncherArgs
