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
        val propertiesLocation =
          getFiles.pwd.resolve("project/build.properties")

        val properties =
          BuildProperties.read(getFiles.contents(propertiesLocation))

        val jarLocation = DownloadSbtJar.acquireSbtJar(properties.sbtVersion)

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

        LaunchSbtJar.launch(jdkHome, jarLocation, arguments)

      catch
        case n: NetworkError    => 
          getLogger.error(s"(network) ${n.msg}")
        case n: DownloadJdk.Err =>
          getLogger.error(s"(downloading jdk) ${n.msg}")
        case other => throw other
end hello
