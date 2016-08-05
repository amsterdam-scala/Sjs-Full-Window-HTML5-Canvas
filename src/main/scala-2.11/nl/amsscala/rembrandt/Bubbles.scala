package nl.amsscala.rembrandt

import org.scalajs.dom
import org.threeten.bp

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport



trait Bubbles {
  def resize(): Unit = {}

    dom.window.setInterval(() => resize(), 50)

}
