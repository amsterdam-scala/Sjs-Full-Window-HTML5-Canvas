package nl.amsscala.rembrandt

import org.scalajs.dom
import org.threeten.bp

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

/**
  * Use this code as a template for resizeable full-window canvas applications. In index.html, replace %main application
  * class% with the application's main class name (whatever packaged name you give to the copy of this file).
  */
@JSExport
object Main extends js.JSApp with Resizeable with Fancy with Clock {
  lazy val timezone = js.Dynamic.global.jstz.determine().name().toString

  /**
    * Alternative method is enforced by JSApp which is necessary for the parameterless main call of the sbt
    * generated launcher when generation is set in build.sbt as: `persistLauncher in Compile := true`
    */
  @JSExport
  def main(): Unit = main(dom.document.getElementById("canvas").asInstanceOf[dom.html.Canvas])

  /**
    * The method initially called when the HTML page is loaded typically by its `Main().main(...)`
    * scripting. E.g. `...rembrandt.Main().main(document.getElementById('canvas'))`
    * This first calls the setup method, then performs the rest of the application logic
    * (which needs to be implemented).
    *
    * @param canvas The HTML element, the container for graphics
    */
  @JSExport
  def main(canvas: dom.html.Canvas): Unit = {
    val (date, formatter) = (bp.LocalDateTime.now(bp.ZoneId.of("+02:00"))
      , bp.format.DateTimeFormatter.ofPattern("d-MMM-yyyy HH:mm:ss", java.util.Locale.US))

    println(s"Main started at ${formatter.format(date)}.")

    // Initialize the canvas and refresh continuously.
    dom.window.setInterval(() => intialResize(canvas, List(resizeCanvas, fancy, clock), timezone), 40)

    // ... rest of the application logic
  }

}
