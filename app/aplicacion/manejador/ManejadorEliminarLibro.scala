package aplicacion.manejador

import aplicacion.comando.ComandoLibro
import aplicacion.fabrica.FabricaLibro
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import dominio.servicio.ServicioEliminarLibro
import javax.inject.Inject

import scala.concurrent.Future

class ManejadorEliminarLibro @Inject() (
                                         repositorioLibro: RepositorioLibro,
                                         servicioEliminarLibro: ServicioEliminarLibro) {

  def ejecutar(id: String): Future[Option[Libro]] = {
    servicioEliminarLibro.eliminarLibro(id)
  }
}
