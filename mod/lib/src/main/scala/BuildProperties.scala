package sabotage.lib

import java.io.ByteArrayInputStream

import language.experimental.saferExceptions

case class BuildProperties(
    sbtVersion: String,
    jdk: Option[String] = None,
    jdkIndex: Option[String] = None
):
  def jdkUrl(index: JvmIndex, target: Platform.Target): Option[String] =
    jdk.map: jdkSpec =>
      val (vendor, versionSpec) = jdkSpec match
        case s"$vendor:$version" => (vendor, version)
        case other               => ("adoptium", other)

      val versionKey = versionSpec match
        case s"1.$ver" => versionSpec
        case other     => s"1.$other"

      val vendorKey = s"jdk@$vendor"

      val spec = index.drillDown(target)(vendorKey)

      spec(versionKey)
end BuildProperties

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
