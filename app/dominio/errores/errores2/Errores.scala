package dominio.errores.errores2

import play.api.mvc.Results.Status
import play.api.mvc.{RequestHeader, Result}

import scala.concurrent.Future

sealed trait Errores

sealed trait Detalle extends Errores {
  val mensaje: String
}

object Errores {
  final case class ValorObligatorio(mensaje: String) extends Detalle

  def valorObligatorio(mensaje: String = "el campo debe ser obligatorio"): Detalle = ValorObligatorio(mensaje)

  def error(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(
      Status(statusCode)("A ocurrido un error durante el proceso: " + message)
    )
  }
}

