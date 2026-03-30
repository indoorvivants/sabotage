package sabotage.lib

def Usage(using Context) = s"""
Usage: sbt [options]

  -h | --help         print this message
  -v | --verbose      this runner is chattier
  -V | --version      print sbt version information
  --numeric-version   print the numeric sbt version (sbt sbtVersion)
  --script-version    print the version of sbt script
  shutdownall         shutdown all running sbt-launch processes
  -d | --debug        set sbt log level to debug
  -debug-inc | --debug-inc
                      enable extra debugging for the incremental debugger
  --no-colors         disable ANSI color codes
  --color=auto|always|true|false|never
                      enable or disable ANSI color codes      (sbt 1.3 and above)
  --supershell=auto|always|true|false|never
                      enable or disable supershell            (sbt 1.3 and above)
  --traces            generate Trace Event report on shutdown (sbt 1.3 and above)
  --client            run native client
  --jvm-client        run JVM client
  --timings           display task timings report on shutdown
  --allow-empty       start sbt even if current directory contains no sbt project
  --sbt-dir   <path>  path to global settings/plugins directory (default: ~/.sbt)
  --sbt-boot  <path>  path to shared boot directory (default: ~/.sbt/boot in 0.11 series)
  --sbt-cache <path>  path to global cache directory (default: operating system specific)
  --ivy       <path>  path to local Ivy repository (default: ~/.ivy2)
  --mem    <integer>  set memory options (default: ${getDefaults.defaultMem})
  --no-share          use all local caches; no sharing
  --no-global         uses global caches, but does not use global ~/.sbt directory.
  --jvm-debug <port>  Turn on JVM debugging, open at the given port.
  --batch             disable interactive mode

  # sbt version (default: from project/build.properties if present, else latest release)
  --sbt-version  <version>   use the specified version of sbt
  --sbt-jar      <path>      use the specified jar as the sbt launcher

  --java-home <path>         alternate JAVA_HOME

  # jvm options and output control
  JAVA_OPTS           environment variable, if unset uses "${getDefaults.defaultJavaOpts
    .mkString(" ")}"
  .jvmopts            if this file exists in the current directory, its contents
                      are appended to JAVA_OPTS
  SBT_OPTS            environment variable, if unset uses "${getDefaults.defaultSbtOpts
    .mkString(" ")}"
  .sbtopts            if this file exists in the current directory, its contents
                      are prepended to the runner args
  /etc/sbt/sbtopts    if this file exists, it is prepended to the runner args
  -Dkey=val           pass -Dkey=val directly to the java runtime
  -J-X                pass option -X directly to the java runtime
                      (-J is stripped)

In the case of duplicated or conflicting options, the order above
shows precedence: JAVA_OPTS lowest, command line options highest.
"""
