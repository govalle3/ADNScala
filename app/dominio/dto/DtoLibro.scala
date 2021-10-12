package dominio.dto

import java.time.LocalDate

case class DtoLibro(titulo: String,
                    publicacion: LocalDate,
                    autor: String,
                    editorial: String,
                    paginas: Option[Int])
