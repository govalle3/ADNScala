package aplicacion

import aplicacion.manejadorComando.Resultado.Respuesta
import cats.data.EitherT
import dominio.errores.ErrorDominio
import monix.eval.Task

import scala.language.implicitConversions

package object manejadorComando {

  type RespuestaComando[T] = EitherT[Task, ErrorDominio, ComandoRespuesta[T]]

  implicit def tranformar[T](respuestaDominio: Respuesta[T]): RespuestaComando[T] = {
    respuestaDominio.map(respuesta => ComandoRespuesta[T](respuesta))
  }

}
