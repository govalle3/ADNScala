package controllers

import infraestructura.persistencia.repositorio.RepositorioLibroImpl
import javax.inject._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(controllerComponents: ControllerComponents,
                               repositorioLibroImpl: RepositorioLibroImpl)
  extends AbstractController(controllerComponents) {
  /*
      Función de ayuda para crear la tabla si esta aún no existe.
     */
  def dbInit: Action[AnyContent] = Action.async { _ =>
    repositorioLibroImpl.dbInit
      .map(_ => Created("Tabla creada"))
      .recover{ex =>
        play.Logger.of("dbInit").debug("Error en dbInit", ex)
        InternalServerError(s"Hubo un error")
      }
  }
}
