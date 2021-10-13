import java.sql.Date

import cats.data.EitherT
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import dominio.servicio.ServicioCrearLibro
import monix.eval.Task
import monix.execution.Scheduler
import org.scalatest.{AsyncWordSpec, MustMatchers}
import org.specs2.mock.Mockito

import scala.concurrent.Future


class StackSpec extends AsyncWordSpec with MustMatchers with Mockito {

  val str: String = "2021-05-05"
  val date: Date = Date.valueOf(str)
  val mockRepoLibro: RepositorioLibro = mock[RepositorioLibro]
  implicit val s: Scheduler = Scheduler.global
  val servicio = new ServicioCrearLibro(mockRepoLibro)

  "ServicioCrearLibro" can {
    "generar un error" when {
      "se va a crear un libro y existe previamente" in {
        val libro = Libro("1", "El olvido", Some(date), "autor", "editorial", Some(400))
        mockRepoLibro.existe(anyString) returns Future.successful(true)
        //mockRepoLibro.insertar(any[Libro]()) returns Future[Option[Libro]]

        val actual = servicio.crearLibro(libro)
        actual.map(libroCreado => {
          libroCreado match {
            case
          }
        })
      }
    }
  }
}