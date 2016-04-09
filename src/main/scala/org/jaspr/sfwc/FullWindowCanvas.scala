package org.jaspr.sfwc

import org.scalajs.dom.{CanvasRenderingContext2D, ClientRect, Event, frames, html, onresize}

object FullWindowCanvas {
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
}
