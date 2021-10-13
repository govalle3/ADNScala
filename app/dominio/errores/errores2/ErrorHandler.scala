package dominio.errores.errores2

import javax.inject.{Inject, Provider, Singleton}
import play.api.{Configuration, Environment, OptionalSourceMapper}
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router

import scala.concurrent._

@Singleton
class ErrorHandler @Inject() (
                               env: Environment,
                               config: Configuration,
                               sourceMapper: OptionalSourceMapper,
                               router: Provider[Router]) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {


  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(
      Status(statusCode)("A client error occurred: " + message)
    )
  }

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(
      InternalServerError("Ocurrio un error en el proceso: " + exception.getMessage)
    )
  }

  override def onBadRequest(request: RequestHeader, message: String): Future[Result] = {
    Future.successful(
      BadRequest(s"Ocurrio un error en la peticion $request" + message)
    )
  }


}
