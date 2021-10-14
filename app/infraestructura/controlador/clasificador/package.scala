package infraestructura.controlador

import dominio.errores.Errores.{ValorExistente, ValorNoExistente, ValorObligatorio, generarMensajeError}
import dominio.errores.{Errores, ErroresList}
import monix.execution.ExecutionModel.AlwaysAsyncExecution
import monix.execution.Scheduler
import monix.execution.schedulers.SchedulerService
import play.api.mvc.Result

package object clasificador {
  import play.api.mvc.Results._

  implicit val scheduler: SchedulerService = Scheduler.fixedPool(name = "main-pool", poolSize = 10, executionModel = AlwaysAsyncExecution)

  def clasificarError[T](err: Errores): Result = err match {
    case error: ValorExistente => BadRequest(generarMensajeError(error.mensaje))
    case error: ValorObligatorio => BadRequest(generarMensajeError(error.mensaje))
    case error: ValorNoExistente => NotFound(generarMensajeError(error.mensaje))
    case error: ErroresList => BadRequest(generarMensajeError(error.errores.toList.map(_.mensaje).mkString(" - ")))
    case _ => InternalServerError(generarMensajeError("Error, contacte al administrador"))
  }




}
