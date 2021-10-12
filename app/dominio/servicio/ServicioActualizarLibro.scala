package dominio.servicio

import cats.implicits.catsSyntaxEitherId
import dominio.errores.{DetalleErrorDominio, ErrorDominio}
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

    def validarSiExisteLibro(libro: Libro): Future[Either[Boolean, DetalleErrorDominio]] = {
      repositorioLibro.existe(libro.id).map {
        case false => ErrorDominio.noExiste("Libro No existe").asRight
        case true => true.asLeft
      }
    }
  }


