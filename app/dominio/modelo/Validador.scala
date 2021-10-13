package dominio.modelo

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
