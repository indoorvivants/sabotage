package sabotage.lib

case class Version(major: Int, minor: Int, patch: Int)
object Version:
  given Ordering[Version] = Ordering.by(v => (v.major, v.minor, v.patch))
