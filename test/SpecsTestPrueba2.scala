import java.sql.Date

import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import dominio.servicio.ServicioCrearLibro
import org.mockito.ArgumentMatchers.any
import org.scalatest.Matchers
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import org.specs2.mock.Mockito.{anyString, theStubbed}
import slick.util.SQLBuilder.Result

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class SpecsTestPrueba2 extends PlaySpec  {

  val str: String = "2021-05-05"
  val date: Date = Date.valueOf(str)
  val mockRepoLibro: RepositorioLibro = mock[RepositorioLibro]

  "ServicioCrearLibro" can {
    "Crear un usuario" in {
      val libro = Libro("1", "El olvido", date, "autor", "editorial", Some(400))
      mockRepoLibro.existe(anyString) returns Future(true)
      //mockRepoLibro.insertar(libro) returns Option[Libro]

      val servicio = new ServicioCrearLibro(mockRepoLibro)
      val respuesta = servicio.crearLibro(libro)

      a [IllegalArgumentException] should be thrownBy {
        servicio.crearLibro(libro)
      }
    }
  }



}