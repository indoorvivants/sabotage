package sabotage.lib

import java.nio.file.Path

object BootstrapJdk:

  def bootstrap(
      properties: BuildProperties
  )(using
      Env,
      Network,
      Files,
      Log,
      Context,
      // Can't use `throws` here because SN breaks at link time
      CanThrow[JvmIndex.Err | Network.Err | DownloadJdk.Err]
  ): Path =
    Timings.time("bootstrapping JDK"):
      bootstrapImpl(properties)

  private def bootstrapImpl(
      properties: BuildProperties
  )(using
      Env,
      Network,
      Files,
      Log,
      Context,
      // Can't use `throws` here because SN breaks at link time
      CanThrow[JvmIndex.Err | Network.Err | DownloadJdk.Err]
  ): Path =
    properties.jdk match
      case None          => Files.get.resolve(Env.get.variables("JAVA_HOME"))
      case Some(jdkSpec) =>
        val index = DownloadJvmIndex.acquireJvmIndex(
          properties.jdkIndex.getOrElse("coursier")
        )

        val downloadUrl = properties
          .jdkUrl(jdkSpec, index, Platform.target)
          .getOrElse(sys.error(s"no JDK url found matching $jdkSpec"))

        DownloadJdk.download(downloadUrl)
end BootstrapJdk
