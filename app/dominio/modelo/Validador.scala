package dominio.modelo

import cats.implicits.catsSyntaxEitherId
import dominio.errores.Errores

object Validador {

  def valorTituloObligatorio(titulo: String, mensaje: String): Either[Errores, Boolean] = {
     if (titulo.isEmpty) {
       Errores.valorObligatorio(mensaje).asLeft
     } else {
       false.asRight
     }

  }

    def valorAutorObligatorio(autor: String, mensaje: String): Either[Errores, Boolean] = {
      if (autor.isEmpty) {
        Errores.valorObligatorio(mensaje).asLeft
      } else {
        false.asRight
      }
  }

  def valorEditorialObligatorio(editorial: String, mensaje: String): Either[Errores, Boolean] = {
    if (editorial.isEmpty) {
      Errores.valorObligatorio(mensaje).asLeft
    } else {
      false.asRight
    }
  }


}
