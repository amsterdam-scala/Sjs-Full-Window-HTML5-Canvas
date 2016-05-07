import org.scalajs.dom.{CanvasRenderingContext2D, ClientRect, Event, frames, html, onresize}
import scala.scalajs.js.annotation.JSExport

/**
  * Use this code as a template for resizeable full-window canvas applications. In index.html, replace %main application
  * class% with the application's main class name (whatever packaged name you give to the copy of this file).
  */
@JSExport
object MainAppTemplate {
  /**
    * Implement this to paint the canvas on startup and on window resize.
    *
    * @param area The current coordinates of the canvas box within which to paint.
    * @param renderer The renderer object providing canvas painting functions.
    */
  def paint (area: ClientRect, renderer: CanvasRenderingContext2D): Unit = ???

  /**
    * Sets the application to paint a canvas in the full window, now and each time resized.
    *
    * @param canvas The canvas element in the webpage.
    * @param paint A method painting the canvas, taking the rectangle to paint and the rendering context as input.
    */
  def setup (canvas: html.Canvas, paint: (ClientRect, CanvasRenderingContext2D) => Unit): Unit = {
    val renderer = canvas.getContext ("2d").asInstanceOf[CanvasRenderingContext2D]

    def present (): Unit = {
      canvas.width = frames.innerWidth
      canvas.height = frames.innerHeight
      paint (canvas.getBoundingClientRect (), renderer)
    }

    present ()
    onresize = (event: Event) => present ()
  }

  /**
    * The method intially called when the page is loaded. This first calls the setup method, then performs the rest of the
    * application logic (which needs to be implemented).
    *
    * @param canvas
    */
  @JSExport
  def main (canvas: html.Canvas): Unit = {
    setup (canvas, paint)
    // ... rest of the application logic
  }
}
