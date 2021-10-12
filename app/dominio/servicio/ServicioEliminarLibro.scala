package dominio.servicio

import cats.implicits.catsSyntaxEitherId
import dominio.errores.{DetalleErrorDominio, ErrorDominio}
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class ServicioEliminarLibro @Inject()(repositorioLibro: RepositorioLibro)
                                     (implicit ec: ExecutionContext) {

  def eliminarLibro(id: String): Future[Option[Libro]] = {
    for {
      _ <- validarSiExisteLibro(id)
      libro <- repositorioLibro.eliminar(id)
    } yield libro
  }

  def validarSiExisteLibro(id: String): Future[Either[Boolean, DetalleErrorDominio]] = {
    repositorioLibro.existe(id).map {
      case false => throw new IllegalArgumentException(s"Libro ${id} no existe")
      case true => true.asLeft
    }
  }

}
