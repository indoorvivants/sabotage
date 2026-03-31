package sabotage.bin

import sabotage.lib.*

import language.experimental.saferExceptions

@main def hello(arguments: String*) =
  RealWorld.use:
    CurlNetwork.use:
      try
        val args = ReadLauncherArgs.read(arguments)
        Logger.info(args.toString())

        if args.help then
          println(Usage)
          sys.exit(0)

        CheckWorkingDirectory.check(args)

        val propertiesLocation =
          Files.get.pwd.resolve("project/build.properties")

        val properties =
          ReadBuildProperties.read(propertiesLocation)

        val sbtVersion = args.sbtVersion.getOrElse(properties.sbtVersion)

        val jarLocation =
          args.sbtJar.getOrElse(BootstrapSbtJar.bootstrap(sbtVersion))

        val jdkHome =
          args.javaHome.getOrElse(BootstrapJdk.bootstrap(properties))

        if ShouldUseSbtn.decide(properties.readSbtVersion, args) then
          val sbtnLocation =
            BootstrapSbtn.bootstrap(Context.defaults.sbtnVersion)
          LaunchSbt.launchNativeClient(jdkHome, sbtnLocation, args.pass)
        else LaunchSbt.launchJar(jdkHome, jarLocation, args.pass)

      catch
        case n: Network.Err =>
          Logger.error(s"(network) ${n.msg}")
        case n: DownloadJdk.Err =>
          Logger.error(s"(downloading jdk) ${n.msg}")
        case n: BootstrapSbtn.Err =>
          Logger.error(s"(downloading sbtn) ${n.msg}")
        case n: ReadLauncherArgs.Err =>
          Logger.error(s"(parsing arguments) ${n.msg}")
        case n: JvmIndex.Err =>
          Logger.error(s"(jvm index) ${n.msg}")
        case _: CheckWorkingDirectory.NoSbtBuildError.type =>
          System.err.println(EmptyFolderMessage)
        case n: LaunchSbt.Err =>
          Logger.error(s"(launching sbt) ${n.msg}")
        case other => throw other
end hello
