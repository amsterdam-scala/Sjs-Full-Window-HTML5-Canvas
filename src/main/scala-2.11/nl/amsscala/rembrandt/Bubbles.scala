package nl.amsscala
package rembrandt

import org.scalajs.dom

trait Bubbles {
  private lazy val twoPi = 2.0 * math.Pi
  private var waves = Vector.empty[Wave[Double]]

  private def speed = 2

  class Point[T: Numeric](val x: T, val y: T) {
    import Numeric.Implicits._

    def +(p: Point[T]) = new Point(x + p.x, y + p.y)
  }

  case class Wave[T](loc: Point[T], time: Int = 1)

  protected def bubbles(canvas: dom.html.Canvas, renderer: dom.CanvasRenderingContext2D, dummy: => String) = {

    def draw(w: Wave[Double]) = {
      renderer.beginPath()
      renderer.arc(w.loc.x, w.loc.y, speed * w.time, 0, twoPi)
      renderer.stroke()
      w.copy(time = w.time + 1)
    }

    renderer.strokeStyle = "magenta"
    renderer.lineWidth = 5

    waves = waves.map(draw).takeWhile { w => w.time * 8 * speed < canvas.width || w.time * 5 * speed < canvas.height }

    canvas.onmousedown = { (e: dom.MouseEvent) => waves +:= Wave(new Point(e.clientX, e.clientY)) }
  }


}
