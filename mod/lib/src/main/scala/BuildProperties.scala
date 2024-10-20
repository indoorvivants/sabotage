package sabotage.lib

import java.io.ByteArrayInputStream

import language.experimental.saferExceptions

case class BuildProperties(
    sbtVersion: String,
    jdk: Option[String] = None,
    jdkIndex: Option[String] = None
)

object BuildProperties:
  def read(contents: String)(using Context) =
    import java.util.Properties

    val prop = new Properties()

    prop.load(new ByteArrayInputStream(contents.getBytes()))

    BuildProperties(
      prop.getOrDefault("sbt.version", "1.10.3").asInstanceOf[String],
      Option(prop.get("jdk.version").asInstanceOf[String]),
      Option(prop.get("jdk.index").asInstanceOf[String])
    )
  end read
end BuildProperties
