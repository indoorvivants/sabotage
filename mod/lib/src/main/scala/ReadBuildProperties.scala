package sabotage.lib

import java.nio.file.Path

object ReadBuildProperties:
  def read(path: Path)(using Context, Files, Log): BuildProperties =
    Timings.time("reading build properties"):
      if Files.isFile(path) then BuildProperties.read(Files.contents(path))
      else BuildProperties.default
end ReadBuildProperties
