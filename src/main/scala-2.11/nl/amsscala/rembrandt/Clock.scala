package nl.amsscala
package rembrandt

import org.scalajs.dom
import org.threeten.bp

import scala.scalajs.js

trait Clock extends Fancy {

  protected def dateTimeStringOfPattern(pat: String): String = {
    val dateTime = new js.Date()

    def timezoneOffset = bp.Duration.ofMinutes(dateTime.getTimezoneOffset())

    def tzId(timezoneOffset: bp.Duration) = bp.ZoneId.of(bp.LocalTime.MIDNIGHT.plus(timezoneOffset.abs).
      format(bp.format.DateTimeFormatter.ofPattern(s"${if (timezoneOffset.isNegative) "+" else "-"}HH:mm")))

    // Convert javaScript datetime to java.time
    bp.Instant.ofEpochMilli(dateTime.getTime().toLong).atZone(tzId(timezoneOffset)).
      format(bp.format.DateTimeFormatter.ofPattern(pat, java.util.Locale.US))
  }

  protected def clock(canvas: dom.html.Canvas, renderer: dom.CanvasRenderingContext2D, timezone: => String): Unit = {
    val area = canvas.getBoundingClientRect()
    val dateTime = new js.Date()

    renderer.textAlign = "center"
    renderer.textBaseline = "bottom"
    renderer.font = "75px sans-serif"
    renderer.fillText(dateTimeStringOfPattern("HH:mm:ss"), area.width / 2, area.height / 2)

    renderer.textBaseline = "top"
    renderer.font = "48px sans-serif"
    renderer.fillText(timezone, area.width / 2, area.height / 2)
  }

}
