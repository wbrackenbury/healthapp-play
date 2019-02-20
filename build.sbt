name := """play-healthapp"""

version := "2.7.x"

scalaVersion := "2.12.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
 
libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test,
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4",
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0",
  "com.github.t3hnar" %% "scala-bcrypt" % "3.1"
)

scalacOptions ++= Seq(
    "-feature",
    "-deprecation",
    "-Xfatal-warnings"
)
