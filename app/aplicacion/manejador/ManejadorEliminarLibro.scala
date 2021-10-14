package aplicacion.manejador

import dominio.errores.Errores
import dominio.modelo.Libro
import dominio.servicio.ServicioEliminarLibro
import javax.inject.Inject

import scala.concurrent.Future

class ManejadorEliminarLibro @Inject() (servicioEliminarLibro: ServicioEliminarLibro) {

  def ejecutar(id: String):  Future[Either[Errores, Option[Libro]]] = {
    servicioEliminarLibro.eliminarLibro(id)
  }
}
