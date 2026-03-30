package sabotage.lib

import java.nio.file.Path
import scala.scalanative.libc.errno
import scala.scalanative.posix.unistd

import scalanative.unsafe.*

object LaunchSbt:
  def launchNativeClient(
      jdkHome: Path,
      sbtnLocation: Path,
      arguments: Seq[String]
  )(using Env, Logger) =
    Zone:
      getLogger.info(
        s"Launching SBT native client [$sbtnLocation] using jdkHome [$jdkHome] and arguments [${arguments.mkString(" ")}]"
      )

      val envP = encode(
        Seq(
          s"JAVA_HOME=$jdkHome",
          s"JDK_HOME=$jdkHome",
          s"PATH=$jdkHome/bin:${getEnv.variables("PATH")}"
        )
      )
      val argsP = encode(sbtnLocation.toString() +: arguments)

      unistd.environ = envP
      val error = unistd.execve(toCString(sbtnLocation.toString()), argsP, envP)
      if error == -1 then getLogger.error(s"execve failed with ${errno.errno}")
  end launchNativeClient

  def launchJar(jdkHome: Path, jarLocation: Path, arguments: Seq[String])(using
      Env,
      Logger
  ) =
    Zone:
      getLogger.info(
        s"Launching SBT jar [$jarLocation] using jdkHome [$jdkHome] and arguments [${arguments.mkString(" ")}]"
      )
      val java = jdkHome.resolve("bin/java")

      val envP = encode(
        Seq(
          s"JAVA_HOME=$jdkHome",
          s"JDK_HOME=$jdkHome",
          s"PATH=$jdkHome/bin:${getEnv.variables("PATH")}"
        )
      )
      val argsP = encode(
        Seq(java.toString, "-jar", jarLocation.toString()) ++ arguments
      )

      unistd.environ = envP
      val error = unistd.execve(toCString(java.toString()), argsP, envP)
      if error == -1 then getLogger.error(s"execve failed with ${errno.errno}")
  end launchJar

  private def printP(ptr: Ptr[CString]) =
    var i = 0
    while !(ptr + i) != null do
      println(fromCString(!(ptr + i)))
      i += 1

  private def encode(args: Seq[String])(using Zone) =
    val ptr = alloc[CString](args.length + 1)
    for i <- 0 until args.length do !(ptr + i) = toCString(args(i))

    import language.unsafeNulls
    !(ptr + args.length) = null

    ptr

end LaunchSbt
