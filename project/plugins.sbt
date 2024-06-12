addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.5.3")

addSbtPlugin("com.indoorvivants.vcpkg" % "sbt-vcpkg-native" % "0.0.19")

addSbtPlugin("com.indoorvivants" % "bindgen-sbt-plugin" % "0.1.2")

libraryDependencies += "com.indoorvivants.detective" %% "platform" % "0.0.2"
