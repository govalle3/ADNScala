package dominio

import java.sql.Date

import cats.data.EitherT
import cats.implicits._
import cats.implicits.catsSyntaxEitherId
import dominio.errores.{DetalleErrorDominio, ErrorDominio}
import dominio.modelo.Libro
import dominio.persistencia.repositorio.RepositorioLibro
import dominio.servicio.ServicioCrearLibro
import monix.execution.Scheduler
import org.scalatest.{AsyncWordSpec, MustMatchers}
import org.specs2.mock.Mockito

import scala.concurrent.{ExecutionContext, Future}

class ServicioCrearLibroTest extends AsyncWordSpec with MustMatchers with Mockito {


  val mockRepoLibro: RepositorioLibro = mock[RepositorioLibro]
  implicit val s: Scheduler = Scheduler.global
  val servicio = new ServicioCrearLibro(mockRepoLibro)

  val str: String = "2021-05-05"
  val date: Date = Date.valueOf(str)

/*  "ServicioCrearLibro" can {
    "generar un error" when {
      "se va a crear un libro y existe previamente" in {
        val libro = Libro("1", "El olvido", date , "autor", "editorial", Some(400))
        mockRepoLibro.insertar(any[Libro]()) returns Future.successful(anyObject)
        mockRepoLibro.existe(anyString) returns Future.failed(new IllegalArgumentException(s"Libro con id:${libro.id} existente"))

        val respuesta = servicio.crearLibro(libro).value

        respuesta.runToFuture.map( resultado => {
          resultado.isLeft mustBe true
          resultado match {
            case Right(_) => fail("resultado inesperado")
            case Left(error) => error.asInstanceOf[DetalleErrorDominio].mensaje mustBe "Elemento duplicado"
          }
        })

      }*/

    }

/*    "crear usuario" when {
      "usuario no existe previamente" in {
        val usuario = Usuario(1L, "", "", LocalDateTime.now())
        mockRepoUsuario.insertar(any[Usuario]()) returns EitherT(Task(1L.asRight[ErrorDominio]))
        mockRepoUsuario.existe(anyLong) returns Task(false)

        val respuesta = servicio.crear(usuario).run(mockRepoUsuario).value
        respuesta.runToFuture.map(resultado => {
          resultado match {
            case Right(valor) => valor mustBe 1L
            case Left(_) => fail("resultado inesperado")
          }
        });
      }
    }*/
/*  }

}*/
