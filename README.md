# Sabotage


TODO for feature parity:

[ ] Download SBT jar
[ ] Download sbtn 
[ ] Replicate java args behaviour
[ ] Detect "preloaded" directory
[ ] Check for installed Java
[ ] Copy java-rt jar? why?
[ ] Stuff around cygwin
[ ] Pick up ./.sbtopts
[ ] Pick up /etc/sbt/sbtopts
[ ] Pick up .jvmopts
[ ] Handle SBT_OPTS
[ ] Handle JAVA_OPTS

CLI ARGUMENTS:

```
  --no-colors         disable ANSI color codes
  --color=auto|always|true|false|never
                      enable or disable ANSI color codes      (sbt 1.3 and above)
  --supershell=auto|always|true|false|never
                      enable or disable supershell            (sbt 1.3 and above)
  --traces            generate Trace Event report on shutdown (sbt 1.3 and above)
  --timings           display task timings report on shutdown
  --sbt-create        start sbt even if current directory contains no sbt project
  --sbt-dir   <path>  path to global settings/plugins directory (default: ~/.sbt)
  --sbt-boot  <path>  path to shared boot directory (default: ~/.sbt/boot in 0.11 series)
  --sbt-cache <path>  path to global cache directory (default: operating system specific)
  --ivy       <path>  path to local Ivy repository (default: ~/.ivy2)
  --mem    <integer>  set memory options (default: $sbt_default_mem)
  --no-share          use all local caches; no sharing
  --no-global         uses global caches, but does not use global ~/.sbt directory.
  --jvm-debug <port>  Turn on JVM debugging, open at the given port.
  --batch             disable interactive mode

  # sbt version (default: from project/build.properties if present, else latest release)
  --sbt-version  <version>   use the specified version of sbt
  --sbt-jar      <path>      use the specified jar as the sbt launcher

  --java-home <path>         alternate JAVA_HOME

  -Dkey=val           pass -Dkey=val directly to the java runtime
  -J-X                pass option -X directly to the java runtime
                      (-J is stripped)
```



TODO for sexy:

[ ] Bootstrap JDK version from build.properties
[ ] Embed sbtn into the sabotage
