package infraestructura.controlador

import aplicacion.comando.ComandoLibro
import aplicacion.manejador.{ManejadorActualizarLibro, ManejadorCrearLibro, ManejadorEliminarLibro}
import infraestructura.controlador.formateadores.FormateadoresAplicacion
import io.swagger.annotations.{Api, ApiOperation}
import javax.inject.Inject
import play.Logger
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Api(value = "/libros", tags = Array("Controlador comando de  usuarios"))
class ControladorComandoLibro @Inject()(
                                         cc: ControllerComponents,
                                         manejadorCrearLibro: ManejadorCrearLibro,
                                         manejadorEliminarLibro: ManejadorEliminarLibro,
                                         manejadorActualizarLibro: ManejadorActualizarLibro)
  extends AbstractController(cc)
    with FormateadoresAplicacion {

  val logger: Logger.ALogger = play.Logger.of("ControladorComandoLibro")

  @ApiOperation("Crear libro")
  def crear(): Action[JsValue] = Action.async(parse.json) { implicit request =>
      val validador = request.body.validate[ComandoLibro]
      validador.asEither match {
        case Left(error) => Future.successful(BadRequest(error.toString()))
        case Right(libro) =>
          manejadorCrearLibro.ejecutar(libro)
            .map(libros => {
              val j = Json.obj(
                "data" -> libros,
                "message" -> "Libro creado")
              Ok(j)
            })
            .recover {
              case ex =>
                logger.error("Fallo en crear", ex)
                InternalServerError(s"Hubo un error en crear: ${ex.getLocalizedMessage} ")
            }
      }
  }

  @ApiOperation("Eliminar libro")
  def eliminar(id: String): Action[AnyContent] = Action.async {
    implicit request =>
    manejadorEliminarLibro.ejecutar(id)
      .map(libro => {
        val j = Json.obj(
          "data" -> libro,
          "message" -> "Libro eliminado")
        Ok(j)
      })
      .recover {
        case ex =>
          logger.error("Fallo en eliminar", ex)
          InternalServerError(s"Hubo un error eliminar: ${ex.getLocalizedMessage} ")
      }
  }

  def actualizar(id: String): Action[JsValue] = Action.async(parse.json) {
    implicit request =>
      val validador = request.body.validate[ComandoLibro]
      validador.asEither match {
        case Left(error) => Future.successful(BadRequest(error.toString()))
        case Right(libro) =>
          manejadorActualizarLibro.ejecutar(id, libro)
            .map(libros => {
              val j = Json.obj(
                "data" -> libros,
                "message" -> "Libro actualizado")
              Ok(j)
            })
            .recover {
              case ex =>
                logger.error("Fallo en actualizar", ex)
                InternalServerError(s"Hubo un error en actualizar: ${ex.getLocalizedMessage} ")
            }
      }
  }

}
