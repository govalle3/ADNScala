package dominio.servicio

import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class ServicioActualizarLibro @Inject()(repositorioLibro: RepositorioLibro)
                                       (implicit ec: ExecutionContext) {

  def actualizarLibro(id: String, libro: Libro): Future[Option[Libro]] = {
    for {
      _ <- validarSiExisteLibro(libro)
      libro <- repositorioLibro.actualizar(id, libro)
    } yield libro
  }

    def validarSiExisteLibro(libro: Libro): Future[Boolean] = {
      repositorioLibro.existe(libro.id).flatMap {
        case true => Future.failed(throw new IllegalArgumentException(s"Libro con id:${libro.id} no existente"))
        case false => Future.successful(false)
      }
    }
  }


