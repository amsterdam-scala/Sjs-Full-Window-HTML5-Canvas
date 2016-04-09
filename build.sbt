enablePlugins(ScalaJSPlugin)

name := "sfwc"

version := "1.0"

scalaJSStage in Global := FastOptStage

scalaVersion := "2.11.7"

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"

skip in packageJSDependencies := false
