package sabotage.lib

object CheckWorkingDirectory:
  object NoSbtBuildError extends Exception("")

  def check(
      args: LauncherArgs
  )(using Files, CanThrow[NoSbtBuildError.type]): Unit =
    if !args.allowEmpty then
      val pwd = Files.pwd
      val hasSbtFiles = Files.isFile(pwd.resolve("build.sbt")) ||
        Files.isFile(pwd.resolve("project/build.build.properties"))
      if !hasSbtFiles && !args.newCmd then throw NoSbtBuildError
end CheckWorkingDirectory
