import scala.scalanative.build.SourceLevelDebuggingConfig
import com.indoorvivants.detective.Platform
import bindgen.interface.Binding
import bindgen.plugin.BindgenMode

lazy val BINARY_NAME = "sabotage"
lazy val common = Seq(
  scalaVersion := "3.8.2"
)

lazy val root = project.in(file(".")).aggregate(lib, bin)

lazy val lib =
  project
    .in(file("mod/lib"))
    .enablePlugins(ScalaNativePlugin)
    .settings(common)
    .settings(libraryDependencies += "com.lihaoyi" %%% "upickle" % "4.0.2")
    .settings(
      nativeConfig ~= (_.withSourceLevelDebuggingConfig(
        SourceLevelDebuggingConfig.enabled
      )),
      scalacOptions ++= Seq(
        "-experimental",
        "-Yexplicit-nulls",
        "-language:experimental.saferExceptions",
        "-language:strictEquality",
        "-language:experimental.strictEqualityPatternMatching"
      )
    )

lazy val bin = project
  .in(file("mod/bin"))
  .enablePlugins(
    ScalaNativePlugin,
    BindgenPlugin,
    VcpkgNativePlugin,
    ForgeNativeBinaryPlugin
  )
  .dependsOn(lib)
  .settings(common)
  .settings(
    vcpkgDependencies := VcpkgDependencies("curl"),
    vcpkgNativeConfig ~= { _.addRenamedLibrary("curl", "libcurl") },
    buildBinaryConfig ~= { (_).withName("sabotage") },
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
        .withSourceLevelDebuggingConfig(SourceLevelDebuggingConfig.enabled)
    },
    bindgenMode := BindgenMode.Manual(
      scalaDir = (Compile / sourceDirectory).value / "scala" / "generated",
      cDir = (Compile / resourceDirectory).value / "scala-native" / "generated"
    ),
    bindgenBindings := {
      bindgenBindings.value.map(_.withNoLocation(true))
    }
  )
