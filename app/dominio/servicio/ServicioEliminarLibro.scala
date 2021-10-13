package dominio.servicio

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

  def validarSiExisteLibro(id: String): Future[Boolean] = {
    repositorioLibro.existe(id).flatMap {
      case false => Future.failed(throw new IllegalArgumentException(s"Libro $id no existe"))
      case true => Future.successful(true)
    }
  }

}
