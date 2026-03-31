package sabotage.lib

import java.nio.file.Path

object LaunchSbt:
  case class Err(msg: String, cause: Throwable | Null = null)
      extends Exception(msg, cause)

  def launchNativeClient(
      jdkHome: Path,
      sbtnLocation: Path,
      arguments: Seq[String]
  )(using Env, Logger, Proc, CanThrow[Err]) =
    Logger.info(
      s"Launching SBT native client [$sbtnLocation] using jdkHome [$jdkHome] and arguments [${arguments.mkString(" ")}]"
    )

    val env =
      Map(
        "JAVA_HOME" -> jdkHome.toString(),
        "JDK_HOME" -> jdkHome.toString(),
        "PATH" -> s"$jdkHome/bin:${Env.variables("PATH")}"
      )
    Proc
      .execve(sbtnLocation.toString(), env, arguments) match
      case Some(value) => throw Err(value)
      case None        =>
  end launchNativeClient

  def launchJar(jdkHome: Path, jarLocation: Path, arguments: Seq[String])(using
      Env,
      Proc,
      Logger,
      CanThrow[Err]
  ): Unit =
    Logger.info(
      s"Launching SBT jar [$jarLocation] using jdkHome [$jdkHome] and arguments [${arguments.mkString(" ")}]"
    )
    val java = jdkHome.resolve("bin/java")

    val env =
      Map(
        "JAVA_HOME" -> jdkHome.toString(),
        "JDK_HOME" -> jdkHome.toString(),
        "PATH" -> s"$jdkHome/bin:${Env.variables("PATH")}"
      )

    val args = Seq("-jar", jarLocation.toString()) ++ arguments

    Proc
      .execve(java.toString(), env, args) match
      case Some(value) => throw Err(value)
      case None        =>

  end launchJar
end LaunchSbt
