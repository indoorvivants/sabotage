package sabotage.lib

import java.nio.file.Paths

object ReadLauncherArgs:
  case class Err(msg: String, cause: Throwable | Null = null)
      extends Exception(msg, cause)

  def read(argumentsSeq: Seq[String]): LauncherArgs throws Err =
    var args = LauncherArgs.default
    val arguments = argumentsSeq.toArray

    inline def set(f: LauncherArgs => LauncherArgs) =
      args = f(args)

    val pass = List.newBuilder[String]
    val javaOpts = List.newBuilder[String]
    val sbtOpts = List.newBuilder[String]
    val skip = collection.mutable.Set.empty[Int]

    arguments.zipWithIndex.foreach: (arg, idx) =>
      def getNext() =
        if idx == arguments.length - 1 then
          throw Err(s"Expected an argument after $arg, but got to the end")
        else
          val next = arguments(idx + 1)
          skip.add(idx + 1)
          next

      arg match
        case "--help" | "-help" | "-h" =>
          set(_.copy(help = true))

        case "--version" | "-version" | "-V" =>
          set(_.copy(printVersion = true))

        case "--verbose" | "-verbose" | "-v" =>
          set(_.copy(verbose = true))

        case "--server" =>
          set(_.copy(server = true))

        case "--client" =>
          set(_.copy(client = true))

        case "--jvm-client" =>
          set(_.copy(jvmClient = true))
          sbtOpts += "--client"

        case "--no-colors" | "-no-colors" =>
          set(_.copy(noColors = true))

        case "--no-share" | "-no-share" =>
          set(_.copy(noShare = true))

        case "--no-global" | "-no-global" =>
          set(_.copy(noGlobal = true))

        case "--traces" | "-traces" =>
          set(_.copy(traces = true))

        case "--timings" | "-timings" =>
          set(_.copy(timings = true))

        case s"-J$javaArg" =>
          javaOpts += javaArg

        case s"-D$key=$value" =>
          javaOpts += s"-D$key=$value"

        case "-allow-empty" | "--allow-empty" | "-sbt-create" |
            "--sbt-create" =>
          set(_.copy(allowEmpty = true))

        case "new" | "init" =>
          set(_.copy(newCmd = true))

        case "shutdownall" =>
          set(_.copy(shutdownallCmd = true))

        case "--sbt-jar" | "-sbt-jar" =>
          val jar = Paths.get(getNext())
          set(_.copy(sbtJar = Some(jar)))

        case "--sbt-cache" | "-sbt-cache" =>
          val path = Paths.get(getNext())
          set(_.copy(sbtCache = Some(path)))

        case "--sbt-dir" | "-sbt-dir" =>
          val path = Paths.get(getNext())
          set(_.copy(globalDir = Some(path)))

        case "--sbt-boot" | "-sbt-boot" =>
          val path = Paths.get(getNext())
          set(_.copy(bootDir = Some(path)))

        case s"--color=$value" =>
          set(_.copy(color = Some(value)))

        case s"-color=$value" =>
          set(_.copy(color = Some(value)))

        case "--java-home" | "-java-home" =>
          val path = Paths.get(getNext())
          set(_.copy(javaHome = Some(path)))

        case "-Dsbt.color=never" | "-Dsbt.log.noformat=true" =>
          set(_.copy(noColors = true))
          javaOpts += arg

        case "--mem" =>
          set(_.copy(mem = Some(getNext().toInt)))

        case "--sbt-version" =>
          set(_.copy(sbtVersion = Some(getNext())))

        case other if !skip.contains(idx) =>
          pass += other
        case _ =>
      end match

    args.copy(
      pass = pass.result(),
      javaOpts = javaOpts.result,
      sbtOpts = sbtOpts.result()
    )
  end read
end ReadLauncherArgs
