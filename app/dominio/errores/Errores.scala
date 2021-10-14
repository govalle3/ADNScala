package dominio.errores

import java.text.SimpleDateFormat
import java.util.Calendar

import cats.data.NonEmptyList
import play.api.libs.json.{JsObject, Json}

sealed trait Errores

final case class ErroresList(errores: NonEmptyList[Detalle]) extends Errores

sealed trait Detalle extends Errores {
  val mensaje: String
}

object Errores {

  final case class ValorExistente(mensaje: String) extends Detalle
  final case class ValorObligatorio(mensaje: String) extends Detalle
  final case class ValorNoExistente(mensaje: String) extends Detalle

  def valorExistente(mensaje: String = "valor existente"): Detalle = ValorExistente(mensaje)
  def valorObligatorio(mensaje: String = "valor obligatorio"): Detalle = ValorObligatorio(mensaje)
  def valorNoExistente(mensaje: String = "valor no existente"): Detalle = ValorNoExistente(mensaje)


  def generarMensajeError(mensaje: String): JsObject = {
    Json.obj("Fecha" -> obtenerFecha, "mensaje" -> mensaje)
  }

  private def obtenerFecha: String = {
    s"Fecha: -$getFecha"
  }

  private def getFecha: String = {
    val now = Calendar.getInstance().getTime
    val format = new SimpleDateFormat("yy-MM-dd-HH:mm:ss:SSS")
    format.format(now)
  }

}

