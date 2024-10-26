
scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "TextBasedAdventureGame",
    version := "1.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test,
  )
