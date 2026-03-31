package sabotage.lib

object ReadJavaEnvVariables:
  def read(using
      Env
  ): (
      JAVA_OPTS: Seq[String],
      JAVA_TOOL_OPTIONS: Seq[String],
      JDK_JAVA_OPTIONS: Seq[String]
  ) =
    IMPROVE("Splitting on spaces might not be enough")
    def readFromEnv(name: String) =
      Env.variables.get(name).toSeq.flatMap(_.split(' '))

    (
      JAVA_OPTS = readFromEnv("JAVA_OPTS"),
      JAVA_TOOL_OPTIONS = readFromEnv("JAVA_TOOL_OPTIONS"),
      JDK_JAVA_OPTIONS = readFromEnv("JDK_JAVA_OPTIONS")
    )
  end read
end ReadJavaEnvVariables
