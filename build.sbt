resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

ThisBuild / scalaVersion := "2.12.10"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """library""",
    libraryDependencies ++= Seq(
      guice,

      "org.reactivemongo" % "play2-reactivemongo_2.12" % "1.0.3-play28",
      "org.reactivemongo" %% "reactivemongo-play-json-compat" % "1.0.1-play28",
      // Provide BSON serialization for reactive mongo
      "org.reactivemongo" %% "reactivemongo-bson-compat" % "0.20.13",
      // Provide JSON serialization for Joda-Time
      "com.typesafe.play" %% "play-json-joda" % "2.7.4",
      "io.monix" %% "monix" % "3.3.0",
      "org.webjars" % "swagger-ui" % "3.45.0",
      "com.github.t3hnar" %% "scala-bcrypt" % "4.3.0",

      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "org.scalamock" %% "scalamock" % "5.1.0" % Test,

    )
  )







