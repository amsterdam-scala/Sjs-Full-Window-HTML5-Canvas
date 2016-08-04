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
object Main extends js.JSApp {
  val timezone = js.Dynamic.global.jstz.determine()

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

    setup(canvas, paint)
    // ... rest of the application logic
  }

  /**
    * Alternative method is enforced by JSApp which is necessary for the parameterless main call of the sbt
    * generated launcher when generation is set in build.sbt as: `persistLauncher in Compile := true`
    */
  @JSExport
  def main(): Unit = {
    main(dom.document.getElementById("canvas").asInstanceOf[dom.html.Canvas])
  }

  /**
    * Implement this to paint the canvas on startup and on window resize.
    *
    * @param area     The current coordinates of the canvas box within which to paint.
    * @param renderer The renderer object providing canvas painting functions.
    */
  def paint(area: dom.ClientRect, renderer: dom.CanvasRenderingContext2D): Unit = {
    // Example code
    val gradient = renderer.createLinearGradient(area.width / 2 - 200, 0, area.width / 2 + 200, 0)
    gradient.addColorStop(0, "red")
    gradient.addColorStop(0.5, "green")
    gradient.addColorStop(1, "blue")
    renderer.fillStyle = gradient

    renderer.fillRect(0 + 50, 0 + 50, area.width - 100, area.height - 100)

    renderer.clearRect(0 + 75, 0 + 75, area.width - 150, area.height - 150)

    renderer.textAlign = "center"
    renderer.textBaseline = "bottom"

    val dateTime = new js.Date()
    renderer.font = "75px sans-serif"
    renderer.fillText(
      Seq(dateTime.getHours(), dateTime.getMinutes(), dateTime.getSeconds()).map(num => f"$num%02d").mkString(":"),
      area.width / 2,
      area.height / 2
    )

    renderer.textBaseline = "top"
    renderer.font = "48px sans-serif"

    renderer.fillText(
      timezone.name().toString,
      area.width / 2,
      area.height / 2
    )
  }

  /**
    * Sets the application to paint a canvas in the full window, now and each time resized.
    *
    * @param canvas The canvas element in the web page.
    * @param paint  A method painting the canvas, taking the rectangle to paint and the rendering context as input.
    */
  def setup(canvas: dom.html.Canvas, paint: (dom.ClientRect, dom.CanvasRenderingContext2D) => Unit): Unit = {
    val renderer = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    def present(): Unit = {
      canvas.width = dom.window.innerWidth.toInt
      canvas.height = dom.window.innerHeight.toInt
      paint(canvas.getBoundingClientRect(), renderer)
    }

    // Initialize the canvas.
    present()
    // Handle the event of resizing.
    dom.window.onresize = (event: dom.Event) => present()
  }

}
