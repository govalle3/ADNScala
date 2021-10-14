package dominio.servicio

import java.sql.Date

import dominio.errores.{Detalle, Errores}
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import monix.execution.Scheduler
import org.scalatest.{AsyncWordSpec, MustMatchers}
import org.specs2.mock.Mockito

import scala.concurrent.Future

class ServicioActualizarLibroTest extends AsyncWordSpec with MustMatchers with Mockito {

  val str: String = "2021-05-05"
  val date: Date = Date.valueOf(str)
  val mockRepoLibro: RepositorioLibro = mock[RepositorioLibro]
  implicit val s: Scheduler = Scheduler.global
  val servicio = new ServicioActualizarLibro(mockRepoLibro)

  "ServicioActualizarLibro" can {
    "generar un error" when {
      "se va a actualizar un libro y no existe previamente" in {
        val libro = Libro("1", "El olvido", Some(date), "autor", "editorial", Some(400))
        mockRepoLibro.existe(anyString) returns Future.successful(false)
        mockRepoLibro.actualizar("1",libro) returns Future.successful(Option(libro))

        val actual = servicio.actualizarLibro("1",libro)
        actual.flatMap(either => {
          either.isLeft mustBe true
          either match {
            case Left(errores: Errores) => errores.asInstanceOf[Detalle].mensaje mustBe "valor no existente"
            case Right(_) => fail("resultado inesperado")
          }
        })
      }
    }

    "actualizar libro" when {
      "libro existe previamente" in {
        val libro = Libro("1", "El olvido", Some(date), "autor", "editorial", Some(400))
        mockRepoLibro.existe(anyString) returns Future.successful(true)
        mockRepoLibro.actualizar("1",libro) returns Future.successful(Option(libro))

        val actual = servicio.actualizarLibro("1",libro)
        actual.map(either => {
          either.isRight mustBe true
          either match {
            case Left(_) => fail()
            case Right(libro) => libro.get.paginas.get mustBe 400
          }
        })
      }
    }
  }
}
