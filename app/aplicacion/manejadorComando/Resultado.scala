
package aplicacion.manejadorComando

import cats.data.EitherT
import dominio.errores.ErrorDominio
import monix.eval.Task

object Resultado {

  type Respuesta[T] = EitherT[Task, ErrorDominio, T]

}
