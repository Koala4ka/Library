ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always

logLevel := Level.Warn

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
resolvers += "iheart-sbt-plugin-releases" at "https://dl.bintray.com/iheartradio/sbt-plugins"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.19")
