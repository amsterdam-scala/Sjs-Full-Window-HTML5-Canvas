name := "FullWindowHtml5Canvas"
version := "0.0"
organization := "nl.amsscala"
organizationName := "Amsterdam.scala Meetup Group"
organizationHomepage := Some(url("http://www.meetup.com/amsterdam-scala/"))
homepage := Some(url("http://github.com/amsterdam-scala/Sjs-Full-Window-HTML5-Canvas"))

startYear := Some(2016)

description := "Scala.js application using HTML5 canvas to fill the window, repainted on window resize."

licenses += "EUPL v.1.1" -> url("http://joinup.ec.europa.eu/community/eupl/og_page/european-union-public-licence-eupl-v11")

scalaVersion := "2.11.8"

// Keep this normalizedName constantly the same, otherwise the outputted JS filename will be changed.
normalizedName := "main"

resolvers += Resolver.sonatypeRepo("snapshots")
libraryDependencies ++= Seq(
  "be.doeraene" %%% "scalajs-jquery" % "0.9.0",
  "com.github.karasiq" %%% "scalajs-bootstrap" % "1.1.1",
  "com.lihaoyi" %%% "scalatags" % "0.6.0",
  "com.lihaoyi" %%% "upickle" % "0.4.1",
  "com.lihaoyi" %%% "utest" % "0.4.3" % "test",
 // "io.surfkit" % "scalajs-google-maps_sjs0.6_2.11" % "0.1-SNAPSHOT",
  "org.scala-js" %%% "scalajs-dom" % "0.9.1")

//scalaJSStage in Global := FastOptStage
scalaJSUseRhino in Global := false
jsDependencies += RuntimeDOM

// If true, a launcher script src="../[normalizedName]-launcher.js will
// be generated that always calls the main def indicated by the JSApp.
// persistLauncher in Compile := true
persistLauncher in Test := false

// Will create [normalizedName]-jsdeps.js containing all JavaScript libraries
jsDependencies += "org.webjars" % "jquery" % "3.1.0" / "3.1.0/jquery.js"
// jsDependencies += "org.webjars" % "bootstrap" % "3.3.6" / "bootstrap.js" minified "bootstrap.min.js" dependsOn "2.2.4/jquery.js"

lazy val root = (project in file(".")).enablePlugins(ScalaJSPlugin)

mainClass in Compile := Some("Main")
workbenchSettings
refreshBrowsers <<= refreshBrowsers.triggeredBy(fastOptJS in Compile)

// Workbench has to know how to restart your application.
bootSnippet := "Main().main(document.getElementById('canvas'));"
