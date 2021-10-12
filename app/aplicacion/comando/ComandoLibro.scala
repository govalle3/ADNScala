package aplicacion.comando

import java.sql.Date

case class ComandoLibro(
                         id: String,
                         titulo: String,
                         fechaPublicacion: Date,
                         autor: String,
                         editorial: String,
                         paginas: Option[Int]
                       )
