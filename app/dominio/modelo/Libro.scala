package dominio.modelo

import java.sql.Date

case class Libro(
                  id: String,
                  titulo: String,
                  fechaPublicacion: Option[Date],
                  autor: String,
                  editorial: String,
                  paginas: Option[Int]
                )












