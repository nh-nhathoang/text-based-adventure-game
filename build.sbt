scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "TextBasedAdventureGame",
    version := "1.0",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.18" % Test,
      "org.scalafx" %% "scalafx" % "22.0.0-R33" // Add this line for ScalaFX
    )
  )
