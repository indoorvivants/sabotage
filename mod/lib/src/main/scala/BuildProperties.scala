package sabotage.lib

import java.io.ByteArrayInputStream

import language.experimental.saferExceptions

case class BuildProperties(
    sbtVersion: String,
    sbtnVersion: String,
    jdk: Option[String] = None,
    jdkIndex: Option[String] = None
):
  lazy val readSbtVersion =
    sbtVersion.takeWhile(c => c.isDigit || c == '.') match
      case s"$major.$minor.$patch" =>
        Version(major.toInt, minor.toInt, patch.toInt)

  def jdkUrl(
      jdkSpec: String,
      index: JvmIndex,
      target: Platform.Target
  )(using CanThrow[JvmIndex.Err]): Option[String] =
    val (vendor, versionSpec) = jdkSpec match
      case s"$vendor:$version" => (vendor, version)
      case other               => ("adoptium", other)

    val versionKey = versionSpec match
      case s"1.$ver" => versionSpec
      case other     => s"1.$other"

    val vendorKey = s"jdk@$vendor"

    index.url(target, vendorKey, versionKey)
  end jdkUrl

end BuildProperties

object BuildProperties:
  def read(contents: String)(using Context) =
    import java.util.Properties

    val prop = new Properties()

    prop.load(new ByteArrayInputStream(contents.getBytes()))

    BuildProperties(
      sbtVersion = prop
        .getOrDefault("sbt.version", getDefaults.sbtVersion)
        .asInstanceOf[String],
      sbtnVersion = getDefaults.sbtnVersion,
      jdk = Option(prop.get("jdk.version").asInstanceOf[String]),
      jdkIndex = Option(prop.get("jdk.index").asInstanceOf[String])
    )
  end read
end BuildProperties
