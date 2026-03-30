addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.5.10")

addSbtPlugin("com.indoorvivants.vcpkg" % "sbt-vcpkg-native" % "0.0.21")

addSbtPlugin("com.indoorvivants" % "bindgen-sbt-plugin" % "0.3.1")

addSbtPlugin("com.indoorvivants" % "sbt-doc-view" % "0.0.3")

libraryDependencies += "com.indoorvivants.detective" %% "platform" % "0.0.2"

addSbtPlugin("com.indoorvivants"   % "sbt-forge-native-binary"  % "0.1.1")
