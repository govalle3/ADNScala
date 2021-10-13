package dominio.modelo

import cats.data.ValidatedNel
import cats.implicits.catsSyntaxEitherId
import dominio.errores.errores2.{Detalle, Errores}

object Validador {

  def valorNombreObligatorio(nombre: String, mensaje: String): Boolean = {

    if (nombre.isEmpty) {
      throw new IllegalArgumentException(mensaje)
    } else {
      false
    }

  }

    def valorAutorObligatorio(autor: String, mensaje: String): Boolean = {
      if (autor.isEmpty) {
        throw new IllegalArgumentException(mensaje)
      } else {
        false
      }
  }

  def valorEditorialObligatorio(autor: String, mensaje: String): Boolean = {
    if (autor.isEmpty) {
      throw new IllegalArgumentException(mensaje)
    } else {
      false
    }
  }


}
