package nl.amsscala
package rembrandt

import org.scalajs.dom

trait Fancy {
  // Example code

  protected def fancy(canvas: dom.html.Canvas, renderer: dom.CanvasRenderingContext2D, dummy: => String) = {
    val area =canvas.getBoundingClientRect()
    val gradient = renderer.createLinearGradient(area.width / 2 - 200, 0, area.width / 2 + 200, 0)
    gradient.addColorStop(0, "red")
    gradient.addColorStop(0.5, "green")
    gradient.addColorStop(1, "blue")
    renderer.fillStyle = gradient

    renderer.fillRect(0 + 50, 0 + 50, area.width - 100, area.height - 100)
    renderer.clearRect(0 + 75, 0 + 75, area.width - 150, area.height - 150)
  }
}
