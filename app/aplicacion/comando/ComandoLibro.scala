package aplicacion.comando

import java.sql.Date

case class ComandoLibro(
                         id: String,
                         titulo: String,
                         fechaPublicacion: Option[Date],
                         autor: String,
                         editorial: String,
                         paginas: Option[Int]
                       )
