package org.jaspr.sfwc

import org.scalajs.dom.{html, CanvasRenderingContext2D, ClientRect}
import scala.scalajs.js.annotation.JSExport

/**
  * Use this code as a template for full-window canvas applications. In index.html, replace %main application class%
  * with the application's class name.
  */
@JSExport
object MainAppTemplate {
  def paint (area: ClientRect, renderer: CanvasRenderingContext2D): Unit = ???

  @JSExport
  def main (canvas: html.Canvas): Unit =
    FullWindowCanvas.setup (canvas, paint)
}
