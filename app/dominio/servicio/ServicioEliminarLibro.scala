package dominio.servicio

import cats.implicits.catsSyntaxEitherId
import dominio.errores.Errores
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class ServicioEliminarLibro @Inject()(repositorioLibro: RepositorioLibro)
                                     (implicit ec: ExecutionContext) {

  def eliminarLibro(id: String): Future[Either[Errores, Option[Libro]]] = {
    val res = validarSiExisteLibro(id)
    val res2 = res.flatMap({
      case Left(errores: Errores) => Future.successful(errores.asLeft)
      case Right(_) => repositorioLibro.eliminar(id).map(optionLibro => optionLibro.asRight)
    })
    res2
  }

  def validarSiExisteLibro(id: String): Future[Either[Errores, Boolean]] = {
    repositorioLibro.existe(id).flatMap {
      case false => Future.successful(Errores.valorNoExistente().asLeft)
      case true => Future.successful(false.asRight)
    }
  }

}
