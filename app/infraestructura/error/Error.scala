package infraestructura.error

import java.text.SimpleDateFormat
import java.util.Calendar

import play.api.libs.json.{JsObject, Json}

object Error {

  def generarMensajeError(mensaje: String, tipoError: TipoError): JsObject = {
    Json.obj("codigo" -> getCodigo, "tipo" -> tipoError.toString, "mensaje" -> mensaje)
  }

  private def getCodigo: String = {
    s"ERR-$getFecha"
  }

  private def getFecha: String = {
    val now = Calendar.getInstance().getTime
    val format = new SimpleDateFormat("yyMMddHHmmssSSS")
    format.format(now)
  }

}
