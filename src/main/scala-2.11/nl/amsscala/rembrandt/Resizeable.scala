package nl.amsscala
package rembrandt

import org.scalajs.dom

trait Resizeable {

  protected def resizeCanvas(canvas: dom.html.Canvas, renderer: dom.CanvasRenderingContext2D, dummy: => String) = {
    canvas.width = dom.window.innerWidth.toInt
    canvas.height = dom.window.innerHeight.toInt
  }

  def successif() = {}

  protected def intialResize(canvas: dom.html.Canvas,
                             painters: List[(dom.html.Canvas, dom.CanvasRenderingContext2D, => String) => Unit],
                             context: => String): Unit = {
    val renderer = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    painters.foreach(_ (canvas, renderer, context))

    dom.window.onresize = (event: dom.Event) => painters.foreach(_ (canvas, renderer, context))
  }
}
