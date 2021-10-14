package dominio.modelo

import java.sql.Date

import cats.implicits.catsSyntaxEitherId
import dominio.errores.{Detalle, Errores}
import dominio.persistencia.repositorio.RepositorioLibro
import dominio.servicio.ServicioActualizarLibro
import monix.execution.Scheduler
import org.scalatest.{AsyncWordSpec, MustMatchers}
import org.specs2.mock.Mockito.mock

import scala.concurrent.Future

class LibroTest extends AsyncWordSpec with MustMatchers {

  val str: String = "2021-05-05"
  val date: Date = Date.valueOf(str)
  val mockRepoLibro: RepositorioLibro = mock[RepositorioLibro]
  implicit val s: Scheduler = Scheduler.global

  "Libro" can {
    "generar error" when {
      "no se envía titulo libro" in {
        val libro = Libro("1", "El olvido", Some(date), "autor", "editorial", Some(400))
        val respuesta = Validador.valorTituloObligatorio("", "titulo obligatorio")
        respuesta match {
          case Right(_) => fail()
          case Left(errores: Errores) => errores.asInstanceOf[Detalle].mensaje mustBe "titulo obligatorio"
        }
      }
      "no se envía autor" in {
        val respuesta = Validador.valorAutorObligatorio("", "autor obligatorio")
        respuesta match {
          case Right(_) => fail()
          case Left(errores: Errores) => errores.asInstanceOf[Detalle].mensaje mustBe "autor obligatorio"
        }
      }
      "no se envía una editorial" in {
        val respuesta = Validador.valorEditorialObligatorio("", "editorial obligatorio")
        respuesta match {
          case Right(_) => fail()
          case Left(errores: Errores) => errores.asInstanceOf[Detalle].mensaje mustBe "editorial obligatorio"
        }
      }
    }

  }

}
