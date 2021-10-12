package infraestructura.persistencia.dao

import dominio.modelo.Libro
import dominio.persistencia.dao.DaoLibro
import infraestructura.persistencia.tablas.Tablas
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import slick.jdbc.SQLiteProfile.api._

import scala.concurrent.Future

class DaoLibroImpl @Inject()(val dbConfigProvider: DatabaseConfigProvider)
  extends DaoLibro with Tablas with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val libroQuery = TableQuery[TablaLibro]

  override def listar: Future[Seq[Libro]] = {
    val q = libroQuery.sortBy(_.id) // libro => libro.id
    db.run(q.result)
  }

  override def buscarPorId(id: String): Future[Option[Libro]] = {
    val q = libroQuery.filter(_.id === id) // id => id === this.id
    db.run(q.result.headOption)
  }
}
