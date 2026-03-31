package sabotage.lib

object CheckWorkingDirectory:
  object NoSbtBuildError extends Exception("")

  def check(
      args: LauncherArgs
  )(using Files, CanThrow[NoSbtBuildError.type]): Unit =
    if !args.allowEmpty then
      val pwd = Files.get.pwd
      val hasSbtFiles = Files.get.isFile(pwd.resolve("build.sbt")) ||
        Files.get.isFile(pwd.resolve("project/build.build.properties"))
      if !hasSbtFiles && !args.newCmd then throw NoSbtBuildError
end CheckWorkingDirectory
