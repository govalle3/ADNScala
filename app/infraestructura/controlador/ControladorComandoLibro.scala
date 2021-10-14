package infraestructura.controlador

import aplicacion.comando.ComandoLibro
import aplicacion.manejador.{ManejadorActualizarLibro, ManejadorCrearLibro, ManejadorEliminarLibro}
import dominio.errores.Errores
import infraestructura.controlador.formateadores.FormateadoresAplicacion
import io.swagger.annotations.{Api, ApiOperation}
import javax.inject.Inject
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

  @ApiOperation("Crear libro")
  def crear(): Action[JsValue] = Action.async(parse.json) {
    implicit request =>
    val validador = request.body.validate[ComandoLibro]
    validador.asEither match {
      case Left(error) => Future.successful(BadRequest(error.toString()))
      case Right(comandoLibro) =>
        val res = manejadorCrearLibro.ejecutar(comandoLibro)
        res.map {
          case Left(errores: Errores) => clasificador.clasificarError(errores)
          case Right(value) => Ok(Json.toJson(value))
        }
    }
  }

  @ApiOperation("Eliminar libro")
  def eliminar(id: String): Action[AnyContent] = Action.async {
    implicit request =>
       val res = manejadorEliminarLibro.ejecutar(id)
      res.map {
        case Left(errores: Errores) => clasificador.clasificarError(errores)
        case Right(value) => Ok(Json.toJson(value))
      }
  }

  def actualizar(id: String): Action[JsValue] = Action.async(parse.json) {
    implicit request =>
      val validador = request.body.validate[ComandoLibro]
      validador.asEither match {
        case Left(error) => Future.successful(BadRequest(error.toString()))
        case Right(libro) =>
          val res = manejadorActualizarLibro.ejecutar(id, libro)
          res.map {
            case Left(errores: Errores) => clasificador.clasificarError(errores)
            case Right(value) => Ok(Json.toJson(value))
          }
      }
  }

}
