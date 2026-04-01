package sabotage.bin

import sabotage.lib.*

import language.experimental.saferExceptions
import Timings.time

@main def hello(arguments: String*) =
  RealWorld.use:
    try
      CurlNetwork.use:
        val args = ReadLauncherArgs.read(arguments)
        Log.info(args.toString())

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
        Log.error(s"(network) ${n.msg}")
      case n: DownloadJdk.Err =>
        Log.error(s"(downloading jdk) ${n.msg}")
      case n: BootstrapSbtn.Err =>
        Log.error(s"(downloading sbtn) ${n.msg}")
      case n: ReadLauncherArgs.Err =>
        Log.error(s"(parsing arguments) ${n.msg}")
      case n: JvmIndex.Err =>
        Log.error(s"(jvm index) ${n.msg}")
      case _: CheckWorkingDirectory.NoSbtBuildError.type =>
        System.err.println(EmptyFolderMessage)
      case n: LaunchSbt.Err =>
        Log.error(s"(launching sbt) ${n.msg}")
      case other => throw other
end hello
