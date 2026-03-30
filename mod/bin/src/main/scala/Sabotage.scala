package sabotage.bin

import sabotage.lib.*
import language.experimental.saferExceptions
import java.nio.file.Path
import java.nio.file.Paths
import scala.util.Try
import scala.sys.process.ProcessLogger
import java.io.FileReader
import scala.util.Using
import sabotage.DownloadJvmIndex

/** TODO:
  *   - Fetch sbtn
  */

@main def hello(arguments: String*) =
  RealWorld.use:
    CurlNetwork.use:
      try
        val args = ReadLauncherArgs.read(arguments)
        getLogger.info(args.toString())

        val propertiesLocation =
          getFiles.pwd.resolve("project/build.properties")

        val properties =
          BuildProperties.read(getFiles.contents(propertiesLocation))

        val jarLocation = DownloadSbtJar.acquireSbtJar(properties.sbtVersion)

        lazy val sbtnLocation = DownloadSbtn.acquireSbtn(properties.sbtVersion)

        val jdkHome = properties.jdk match
          case None          => getFiles.resolve(getEnv.variables("JAVA_HOME"))
          case Some(jdkSpec) =>
            val index = DownloadJvmIndex.acquireJvmIndex(
              properties.jdkIndex.getOrElse("coursier")
            )

            val downloadUrl = properties
              .jdkUrl(jdkSpec, index, Platform.target)
              .getOrElse(sys.error(s"no JDK url found matching $jdkSpec"))

            DownloadJdk.download(downloadUrl)
        end jdkHome

        val shouldUseSbtn =
          ShouldUseSbtn.decide(properties.readSbtVersion, args)

        if shouldUseSbtn then
          LaunchSbt.launchNativeClient(jdkHome, sbtnLocation, args.pass)
        else LaunchSbt.launchJar(jdkHome, jarLocation, args.pass)

      catch
        case n: NetworkError =>
          getLogger.error(s"(network) ${n.msg}")
        case n: DownloadJdk.Err =>
          getLogger.error(s"(downloading jdk) ${n.msg}")
        case n: DownloadSbtn.Err =>
          getLogger.error(s"(downloading sbtn) ${n.msg}")
        case n: ReadLauncherArgs.Err =>
          getLogger.error(s"(parsing arguments) ${n.msg}")
        case other => throw other
end hello
