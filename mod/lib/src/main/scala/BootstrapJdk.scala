package sabotage.lib

import java.nio.file.Path

object BootstrapJdk:
  def bootstrap(
      properties: BuildProperties
  )(using
      Env,
      Network,
      Files,
      Logger,
      Context
  ): Path throws (NetworkError | DownloadJdk.Err) =
    properties.jdk match
      case None          => getFiles.resolve(getEnv.variables("JAVA_HOME"))
      case Some(jdkSpec) =>
        val index = DownloadJvmIndex.acquireJvmIndex(
          properties.jdkIndex.getOrElse("coursier")
        )

        val downloadUrl = properties
          .jdkUrl(jdkSpec, index, Platform.target)
          .getOrElse(sys.error(s"no JDK url found matching $jdkSpec"))

        DownloadJdk.download(downloadUrl)
end BootstrapJdk
