package dominio.servicio

import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import javax.inject.Inject
import cats.implicits.catsSyntaxEitherId
import dominio.errores.errores2.Errores

import scala.concurrent.{ExecutionContext, Future}

class ServicioCrearLibro @Inject()(repositorioLibro: RepositorioLibro)
                                  (implicit ec: ExecutionContext) {

  def crearLibro(libro: Libro): Future[Option[Libro]] = {

    val res = validarSiExisteLibro(libro)
    val res2 = res.flatMap {
      case Left(errores) => Future.successful(None)
      case Right(boolean: Boolean) => repositorioLibro.insertar(libro)
    }
    res2
  }

  private def validarSiExisteLibro(libro: Libro): Future[Either[Errores, Boolean]] = {
    repositorioLibro.existe(libro.id).flatMap( {
      case true => Future.successful(Errores.valorExistente().asLeft)
      case false => Future.successful(false.asRight)
    })
  }




}
