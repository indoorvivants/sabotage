import com.indoorvivants.detective.Platform
import bindgen.interface.Binding
import bindgen.plugin.BindgenMode

lazy val BINARY_NAME = "sabotage"
lazy val common = Seq(
  scalaVersion := "3.5.2"
)

lazy val root = project.in(file(".")).aggregate(lib, bin)

lazy val lib =
  project
    .in(file("mod/lib"))
    .enablePlugins(ScalaNativePlugin)
    .settings(common)
    .settings(libraryDependencies += "com.lihaoyi" %%% "upickle" % "4.0.2")

lazy val bin = project
  .in(file("mod/bin"))
  .enablePlugins(ScalaNativePlugin, BindgenPlugin, VcpkgNativePlugin)
  .dependsOn(lib)
  .settings(common)
  .settings(
    vcpkgDependencies := VcpkgDependencies("curl"),
    vcpkgNativeConfig ~= { _.addRenamedLibrary("curl", "libcurl") },
    bindgenBindings += {
      Binding(
        vcpkgConfigurator.value.includes("curl") / "curl" / "curl.h",
        "curl"
      )
        .withCImports(
          List("curl/curl.h")
        )
        .withMultiFile(true)
    },
    nativeConfig := {
      val conf = nativeConfig.value
      val arch64 =
        if (
          Platform.arch == Platform.Arch.Arm && Platform.bits == Platform.Bits.x64
        )
          List("-arch", "arm64")
        else Nil

      conf
        .withLinkingOptions(
          conf.linkingOptions ++ arch64
        )
        .withCompileOptions(
          conf.compileOptions ++ arch64
        )
        .withIncrementalCompilation(true)
    },
    bindgenMode := BindgenMode.Manual(
      scalaDir = (Compile / sourceDirectory).value / "scala" / "generated",
      cDir = (Compile / resourceDirectory).value / "scala-native" / "generated"
    ),
    bindgenBindings := {
      bindgenBindings.value.map(_.withNoLocation(true))
    }
  )

lazy val buildDebugBinary = taskKey[File]("")
buildDebugBinary := {
  writeBinary(
    source = (bin / Compile / nativeLink).value,
    destinationDir = (ThisBuild / baseDirectory).value / "out" / "debug",
    log = sLog.value,
    platform = None
  )
}

lazy val buildReleaseBinary = taskKey[File]("")
buildReleaseBinary := {
  writeBinary(
    source = (bin / Compile / nativeLinkReleaseFast).value,
    destinationDir = (ThisBuild / baseDirectory).value / "out" / "release",
    log = sLog.value,
    platform = None
  )
}

lazy val buildPlatformBinary = taskKey[File]("")
buildPlatformBinary := {
  writeBinary(
    source = (bin / Compile / nativeLinkReleaseFast).value,
    destinationDir = (ThisBuild / baseDirectory).value / "out" / "release",
    log = sLog.value,
    platform = Some(Platform.target)
  )
}

def writeBinary(
    source: File,
    destinationDir: File,
    log: sbt.Logger,
    platform: Option[Platform.Target]
): File = {

  val name = platform match {
    case None => BINARY_NAME
    case Some(target) =>
      val ext = target.os match {
        case Platform.OS.Windows => ".exe"
        case _                   => ""
      }

      BINARY_NAME + "-" + ArtifactNames.coursierString(target) + ext
  }

  val dest = destinationDir / name

  IO.copyFile(source, dest)

  log.info(s"Binary [$name] built in ${dest}")

  dest
}
