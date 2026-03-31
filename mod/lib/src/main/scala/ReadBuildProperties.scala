package sabotage.lib

import java.nio.file.Path

object ReadBuildProperties:
  def read(path: Path)(using Context, Files): BuildProperties =
    if Files.get.isFile(path) then
      BuildProperties.read(Files.get.contents(path))
    else BuildProperties.default
end ReadBuildProperties
