package sabotage.bin

import sabotage.lib.*

import language.experimental.saferExceptions

@main def hello(arguments: String*) =
  RealWorld.use:
    CurlNetwork.use:
      try
        val args = ReadLauncherArgs.read(arguments)
        getLogger.info(args.toString())

        if args.help then
          println(Usage)
          sys.exit(0)

        val propertiesLocation =
          getFiles.pwd.resolve("project/build.properties")

        val properties =
          BuildProperties.read(getFiles.contents(propertiesLocation))

        val sbtVersion = args.sbtVersion.getOrElse(properties.sbtVersion)

        val jarLocation =
          args.sbtJar.getOrElse(BootstrapSbtJar.bootstrap(sbtVersion))

        val jdkHome =
          args.javaHome.getOrElse(BootstrapJdk.bootstrap(properties))

        if ShouldUseSbtn.decide(properties.readSbtVersion, args) then
          val sbtnLocation = BootstrapSbtn.bootstrap(properties.sbtVersion)
          LaunchSbt.launchNativeClient(jdkHome, sbtnLocation, args.pass)
        else LaunchSbt.launchJar(jdkHome, jarLocation, args.pass)

      catch
        case n: NetworkError =>
          getLogger.error(s"(network) ${n.msg}")
        case n: DownloadJdk.Err =>
          getLogger.error(s"(downloading jdk) ${n.msg}")
        case n: BootstrapSbtn.Err =>
          getLogger.error(s"(downloading sbtn) ${n.msg}")
        case n: ReadLauncherArgs.Err =>
          getLogger.error(s"(parsing arguments) ${n.msg}")
        case n: JvmIndex.Err =>
          getLogger.error(s"(jvm index) ${n.msg}")
        case other => throw other
end hello
