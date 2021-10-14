package aplicacion.manejador

import aplicacion.comando.ComandoLibro
import aplicacion.fabrica.FabricaLibro
import cats.implicits.catsSyntaxEitherId
import dominio.errores.Errores
import dominio.modelo.Libro
import dominio.servicio.ServicioCrearLibro
import javax.inject.Inject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ManejadorCrearLibro @Inject()(servicioCrearLibro: ServicioCrearLibro) {

  def ejecutar(comandoLibro: ComandoLibro): Future[Either[Errores, Option[Libro]]] = {
    val res = FabricaLibro.crearLibro(comandoLibro)
    val res2 = res.flatMap({
      case Left(errores: Errores) => Future.successful(errores.asLeft)
      case Right(libro) => servicioCrearLibro.crearLibro(libro)
    })
    res2
  }
}
