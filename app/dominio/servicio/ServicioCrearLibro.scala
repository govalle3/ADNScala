package dominio.servicio

import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import javax.inject.Inject
import cats.implicits.catsSyntaxEitherId
import dominio.errores.Errores

import scala.concurrent.{ExecutionContext, Future}

class ServicioCrearLibro @Inject()(repositorioLibro: RepositorioLibro)
                                  (implicit ec: ExecutionContext) {

  def crearLibro(libro: Libro): Future[Either[Errores, Option[Libro]]] = {
    val res = validarSiExisteLibro(libro)
    val res2 = res.flatMap ({
      case Left(errores: Errores) => Future.successful(errores.asLeft)
      case Right(_) => repositorioLibro.insertar(libro).map(optionLibro => optionLibro.asRight)
    })
    res2
  }

  private def validarSiExisteLibro(libro: Libro): Future[Either[Errores, Boolean]] = {
    repositorioLibro.existe(libro.id).flatMap{
      case true => Future.successful(Errores.valorExistente().asLeft)
      case false => Future.successful(false.asRight)
    }
  }

}
