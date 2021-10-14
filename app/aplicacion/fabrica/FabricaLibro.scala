package aplicacion.fabrica

import aplicacion.comando.ComandoLibro
import cats.implicits.catsSyntaxEitherId
import dominio.errores.Errores
import dominio.modelo.{Libro, Validador}

import scala.concurrent.Future

object FabricaLibro {

  def crearLibro(comandoLibro: ComandoLibro): Future[Either[Errores, Libro]] = {

    val res = Validador.valorTituloObligatorio(comandoLibro.titulo, "Titulo obligatorio")
    val res2 = res match {
      case Left(errores: Errores) => Future.successful(errores.asLeft)
      case Right(_) => Validador.valorAutorObligatorio(comandoLibro.autor, "Autor obligatorio")
    }
    val res3 = res2 match {
      case Left(errores: Errores) => Future.successful(errores.asLeft)
      case Right(_) => Validador.valorAutorObligatorio(comandoLibro.autor, "Autor obligatorio")
    }
    val res4 = res3 match {
      case Left(errores: Errores) => Future.successful(errores.asLeft)
      case Right(_) => Validador.valorEditorialObligatorio(comandoLibro.autor, "editorial obligatorio")
    }
    val res5 = res4 match {
      case Left(errores: Errores) => Future.successful(errores.asLeft)
      case Right(_) => Future.successful(Libro(comandoLibro.id,
        comandoLibro.titulo,
        comandoLibro.fechaPublicacion,
        comandoLibro.editorial,
        comandoLibro.autor,
        comandoLibro.paginas).asRight)
    }
    res5
  }
}
