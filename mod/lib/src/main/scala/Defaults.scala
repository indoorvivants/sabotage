package sabotage.lib

case class Defaults(
    sbtVersion: String = "1.12.5",
    sbtnVersion: String = "1.12.5",
    defaultMem: Int = 1024,
    defaultJavaOpts: Seq[String] = Seq("-dfile.encoding=UTF-8"),
    defaultSbtOpts: Seq[String] = Seq.empty
)
