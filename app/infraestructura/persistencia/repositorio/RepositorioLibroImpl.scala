package infraestructura.persistencia.repositorio

import cats.implicits.catsSyntaxEitherId
import dominio.errores.error.ErrorDominio
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import infraestructura.persistencia.dao.DaoLibroImpl
import infraestructura.persistencia.tablas.Tablas
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.SQLiteProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.{ExecutionContext, Future}

class RepositorioLibroImpl @Inject()(val dbConfigProvider: DatabaseConfigProvider,
                                     daoLibroImpl: DaoLibroImpl)(implicit ec: ExecutionContext)
  extends RepositorioLibro with Tablas with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val libroQuery = TableQuery[TablaLibro]

  override def insertar(libro: Libro): Future[Option[Libro]] = {
    val insert = libroQuery += libro
    db.run(insert)
      .flatMap(_ => daoLibroImpl.buscarPorId(libro.id))
  }

  override def actualizar(id: String, libro: Libro): Future[Option[Libro]] = {
    val q = libroQuery.filter(_.id === libro.id && libro.id.contains(id))
    val update = q.update(libro)
    db.run(update)
      .flatMap(_ => db.run(q.result.headOption))
  }

  override def eliminar(id: String): Future[Option[Libro]] = {
    val q = libroQuery.filter(_.id === id)
    for {
      objetoEliminar <- db.run(q.result.headOption)
      _ <- db.run(q.delete)
    } yield objetoEliminar
  }

  override def existe(id: String): Future[Boolean] = {
    val q = libroQuery.filter(_.id === id).exists
    db.run(q.result)
  }

  /**
   * Función de ayuda para crear la tabla si ésta aún no existe en la base de datos.
   * @return
   */
  def dbInit: Future[Unit] = {
    // Definición de la sentencia SQL de creación del schema
    val createSchema = libroQuery.schema.createIfNotExists
    // db.run Ejecuta una sentencia SQL, devolviendo un Future
    db.run(createSchema)
  }
}
