import java.sql.Date

import akka.actor.Status.{Failure, Success}
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import dominio.servicio.ServicioCrearLibro
import org.scalatestplus.play.PlaySpec
import org.scalatest._
import org.scalatestplus.play._
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import org.specs2.mock.Mockito.{anyString, theStubbed}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class StackSpec extends PlaySpec with MockitoSugar {

  val str: String = "2021-05-05"
  val date: Date = Date.valueOf(str)
  val mockRepoLibro: RepositorioLibro = mock[RepositorioLibro]

  "ServicioCrearLibro" can {
    "generar un error" when {
      "se va a crear un libro y existe previamente" in {
        val libro = Libro("1", "El olvido", date , "autor", "editorial", Some(400))
        val mockServicioCrearLibro = mock[ServicioCrearLibro]
        when(mockServicioCrearLibro.validarSiExisteLibro(libro)).thenReturn(Future.successful(true))
        mockRepoLibro.existe(anyString) returns Future.failed(new IllegalArgumentException(s"Libro con id:${libro.id} existente"))

        val myService = new ServicioCrearLibro(mockRepoLibro)
        val actual = myService.crearLibro(libro)
        actual mustBe Failure


      }
    }
  }





}
