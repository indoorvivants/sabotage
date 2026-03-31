package sabotage.lib

def EmptyFolderMessage(using Files) =
  IMPROVE("Propagate config home to the message")
  s"""
  |Neither build.sbt nor a 'project' directory in the current directory: ${Files.get.pwd}"
  |run 'sbt new', touch build.sbt, or run 'sbt --allow-empty'."
  |
  |To opt out of this check, create $${config_home}/sbtopts with:"
  |--allow-empty"""
