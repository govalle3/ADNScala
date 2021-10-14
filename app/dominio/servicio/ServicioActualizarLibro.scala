package dominio.servicio

import cats.implicits.catsSyntaxEitherId
import dominio.errores.Errores
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class ServicioActualizarLibro @Inject()(repositorioLibro: RepositorioLibro)
                                       (implicit ec: ExecutionContext) {

  def actualizarLibro(id: String, libro: Libro): Future[Either[Errores, Option[Libro]]] = {
    val res = validarSiExisteLibro(libro)
    val res2 = res.flatMap {
      case Left(errores: Errores) => Future.successful(errores.asLeft)
      case Right(_) => repositorioLibro.actualizar(id, libro).map(optionLibro => optionLibro.asRight)
    }
    res2
  }

   private def validarSiExisteLibro(libro: Libro): Future[Either[Errores, Boolean]] = {
      repositorioLibro.existe(libro.id).flatMap {
        case false  => Future.successful(Errores.valorNoExistente().asLeft)
        case true => Future.successful(false.asRight)
      }
    }
  }


