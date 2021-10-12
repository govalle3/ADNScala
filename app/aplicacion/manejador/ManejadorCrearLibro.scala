package aplicacion.manejador

import aplicacion.comando.ComandoLibro
import dominio.servicio.ServicioCrearLibro
import aplicacion.fabrica.FabricaLibro
import dominio.modelo.Libro
import javax.inject.Inject

import scala.concurrent.Future

class ManejadorCrearLibro @Inject() (servicioCrearLibro: ServicioCrearLibro) {

  def ejecutar(comandoLibro: ComandoLibro): Future[Option[Libro]] = {
      val libro: Libro = FabricaLibro.crearLibro(comandoLibro)
      servicioCrearLibro.crearLibro(libro)
  }

}
