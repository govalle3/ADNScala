package dominio.servicio

import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ServicioCrearLibro @Inject()(repositorioLibro: RepositorioLibro)
                                  (implicit ec: ExecutionContext) {

  def crearLibro(libro: Libro): Future[Option[Libro]] = {
    for {
      _ <- validarSiExisteLibro(libro)
      libro <- repositorioLibro.insertar(libro)
    } yield libro
  }

  def validarSiExisteLibro(libro: Libro): Future[Boolean] =  {
    repositorioLibro.existe(libro.id).map {
      case true => throw new IllegalArgumentException(s"Libro con id:${libro.id} existente")
      case false => false
    }
  }

}
