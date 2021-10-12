package aplicacion.manejador

import aplicacion.comando.ComandoLibro
import aplicacion.fabrica.FabricaLibro
import dominio.modelo.Libro
import dominio.servicio.ServicioActualizarLibro
import javax.inject.Inject

import scala.concurrent.Future

class ManejadorActualizarLibro @Inject()(servicioActualizarLibro: ServicioActualizarLibro) {

  def ejecutar(id: String, comandoLibro: ComandoLibro): Future[Option[Libro]] = {
    val libro: Libro = FabricaLibro.crearLibro(comandoLibro)
    servicioActualizarLibro.actualizarLibro(id, libro)
  }
}
