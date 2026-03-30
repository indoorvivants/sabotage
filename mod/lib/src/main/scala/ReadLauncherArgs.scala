package sabotage.lib

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
