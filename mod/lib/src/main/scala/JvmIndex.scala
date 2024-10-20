package sabotage.lib

import upickle.default

import Platform.*

case class JvmIndex(data: JvmIndex.Data):
  def url(
      target: Target,
      vendor: JvmIndex.Vendor,
      version: JvmIndex.Version
  ): Option[String] =
    val osKey = target.os match
      case OS.MacOS   => "darwin"
      case OS.Linux   => "linux"
      case OS.Windows => "windows"

    // TODO: very incomplete
    val archKey = (target.arch, target.bits) match
      case (Arch.Intel, Bits.x64) => "amd64"
      case (Arch.Arm, Bits.x64)   => "arm64"

    for
      os <- data.get(osKey)
      arch <- os.get(archKey)
      vendor <- arch.get(vendor)
      url <- vendor.get(version)
    yield url

    // data.get(osKey).flatMap(_.get(archKey))
  end url
end JvmIndex

object JvmIndex:
  type OS = String
  type Arch = String
  type Vendor = String
  type Version = String

  type Data = Map[OS, Map[Arch, Map[Vendor, Map[Version, String]]]]

  def read(contents: String): JvmIndex =
    JvmIndex(upickle.default.read[Data](contents))
end JvmIndex
