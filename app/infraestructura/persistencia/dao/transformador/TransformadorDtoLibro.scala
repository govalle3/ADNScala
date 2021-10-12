package infraestructura.persistencia.dao.transformador

import java.sql.Date
import java.time.LocalDate

import dominio.dto.DtoLibro

import scala.language.implicitConversions

class TransformadorDtoLibro {

  implicit def fromRegistroToDtoLibro(datos: (Long, String, Date, String, String, Option[Int])): DtoLibro =
    DtoLibro(datos._2, datos._3.toLocalDate, datos._4, datos._5, datos._6)

  implicit def toGeneracionMap(register: Seq[(Long, String, Date, String, String, Option[Int])]): List[DtoLibro] =
    register.map(fromRegistroToDtoLibro).toList

}
