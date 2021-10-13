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

  def listar(): Action[AnyContent] = Action.async {
    manejadorListarLibro.ejecutar()
      .map(libros => {
        val j = Json.obj(
          "data" -> libros,
          "message" -> "Libros listados")
        Ok(j)
      })
  }

  def consultar(id: String): Action[AnyContent] = Action.async {
    manejadorConsultaLibro.ejecutar(id)
      .map(libro => {
        val j = Json.obj(
          "data" -> libro,
          "message" -> "Libro listado")
        Ok(j)
      })
  }


}
