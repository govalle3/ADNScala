package infraestructura.controlador

import aplicacion.manejador.{ManejadorConsultarLibro, ManejadorListarLibro}
import infraestructura.controlador.formateadores.FormateadoresAplicacion
import javax.inject.Inject
import play.Logger
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext.Implicits.global

class ControladorConsultaLibro @Inject()(
                                          cc: ControllerComponents,
                                          manejadorConsultaLibro: ManejadorConsultarLibro,
                                          manejadorListarLibro: ManejadorListarLibro)
  extends AbstractController(cc) with FormateadoresAplicacion {

  val logger: Logger.ALogger = play.Logger.of("ControladorConsultaLibro")

  def listar(): Action[AnyContent] = Action.async {
    manejadorListarLibro.ejecutar()
      .map(libros => {
        val j = Json.obj(
          "data" -> libros,
          "message" -> "Libros listados")
        Ok(j)
      })
      .recover {
        case ex =>
          logger.error("Falló en listar", ex)
          InternalServerError(s"Hubo un error: ${ex.getLocalizedMessage} ")
      }
  }

  def consultar(id: String): Action[AnyContent] = Action.async {
    manejadorConsultaLibro.ejecutar(id)
      .map(libro => {
        val j = Json.obj(
          "data" -> libro,
          "message" -> "Libro listado")
        Ok(j)
      })
      .recover {
        case ex =>
          logger.error("Falló en consultar", ex)
          InternalServerError(s"Hubo un error: ${ex.getLocalizedMessage} ")
      }
  }


}
