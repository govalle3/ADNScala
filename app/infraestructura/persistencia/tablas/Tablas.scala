package infraestructura.persistencia.tablas

import java.sql.Date

import dominio.modelo.Libro
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

trait Tablas {
  self: HasDatabaseConfigProvider[JdbcProfile] =>
  import profile.api._

  class TablaLibro(tag: Tag) extends Table[Libro](tag, "LIBRO") {

    def id: Rep[String] = column[String]("ID", O.PrimaryKey)
    def titulo: Rep[String] = column[String]("TITULO")
    def fechaPublicacion: Rep[Date] = column[Date]("PUBLICACION")
    def autor: Rep[String] = column[String]("AUTOR")
    def editorial: Rep[String] = column[String]("EDITORIAL")
    def paginas: Rep[Option[Int]] = column[Option[Int]]("PAGINAS")

      override def * : ProvenShape[Libro] =
    (id, titulo, fechaPublicacion, autor, editorial, paginas) <> (Libro.tupled, Libro.unapply)

   /* def * : ProvenShape[(String, String, Date, String, String, Option[Int])] =
      (id, titulo, fechaPublicacion, autor, editorial, paginas)*/
  }
}
