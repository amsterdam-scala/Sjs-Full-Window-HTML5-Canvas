package nl.amsscala
package rembrandt

import org.scalajs.dom

trait Resizeable {
  type painterList = List[(dom.html.Canvas, dom.CanvasRenderingContext2D, => String) => Unit]

  private var painters: Option[painterList] = None

  def successiveResize(canvas: dom.html.Canvas, renderer: dom.CanvasRenderingContext2D, dummy: => String) = {
    canvas.width = dom.window.innerWidth.toInt
    canvas.height = dom.window.innerHeight.toInt
  }

  protected def intialResize(canvas: dom.html.Canvas, _painters: painterList, context: => String): Unit = {
    painters = Option(_painters)
    val renderer = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

    painters.get.foreach(_ (canvas, renderer, context))
    dom.window.onresize = (event: dom.Event) => painters.get.foreach(_ (canvas, renderer, context))
  }
}
