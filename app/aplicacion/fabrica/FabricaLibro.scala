package aplicacion.fabrica

import aplicacion.comando.ComandoLibro
import dominio.errores.error.ErrorDominio
import dominio.modelo.{Libro, Validador}
import monix.eval.Task

object FabricaLibro {

  def crearLibro(comandoLibro: ComandoLibro):  Libro = {
      Validador.valorNombreObligatorio(comandoLibro.titulo, "Titulo obligatorio")
      Validador.valorAutorObligatorio(comandoLibro.autor, "Autor obligatorio")
      Validador.valorEditorialObligatorio(comandoLibro.autor, "editorial obligatorio")
      Libro(comandoLibro.id,
        comandoLibro.titulo,
        comandoLibro.fechaPublicacion,
        comandoLibro.editorial,
        comandoLibro.autor,
        comandoLibro.paginas)
    }
  }
