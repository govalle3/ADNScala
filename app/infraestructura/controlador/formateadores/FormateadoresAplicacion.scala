package infraestructura.controlador.formateadores

import aplicacion.comando.ComandoLibro
import dominio.dto.DtoLibro
import dominio.modelo.Libro
import play.api.libs.json.{Format, Json, OFormat}

trait FormateadoresAplicacion {

  implicit val dtoLibroFormat: Format[DtoLibro] = Json.format[DtoLibro]
  implicit val comandoLibroFormat: Format[ComandoLibro] = Json.format[ComandoLibro]
  implicit val serializador: OFormat[Libro] = Json.format[Libro]

}
