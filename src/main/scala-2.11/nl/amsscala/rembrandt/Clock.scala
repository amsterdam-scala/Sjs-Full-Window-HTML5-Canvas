package nl.amsscala.rembrandt

import org.scalajs.dom

import scala.scalajs.js

trait Clock extends Fancy {
  protected def clock(canvas: dom.html.Canvas, renderer: dom.CanvasRenderingContext2D, timezone: String): Unit = {
    val area = canvas.getBoundingClientRect()
    val dateTime = new js.Date()

    renderer.textAlign = "center"
    renderer.textBaseline = "bottom"
    renderer.font = "75px sans-serif"
    renderer.fillText(
      Seq(dateTime.getHours(), dateTime.getMinutes(), dateTime.getSeconds()).map(num => f"$num%02d").mkString(":"),
      area.width / 2,
      area.height / 2)

    renderer.textBaseline = "top"
    renderer.font = "48px sans-serif"
    renderer.fillText(timezone, area.width / 2, area.height / 2)

    lazy val runOnce = dom.window.setInterval(() => {
      println(js.Date())
      fancy(canvas, renderer, timezone)
      clock(canvas, renderer, timezone)
    }, 1000)
  }

}
