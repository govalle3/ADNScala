package aplicacion.fabrica

import aplicacion.comando.ComandoLibro
import dominio.modelo.Libro

object FabricaLibro {

  def crearLibro(comandoLibro: ComandoLibro): Libro = {
    new Libro(comandoLibro.id,
      comandoLibro.titulo,
      comandoLibro.fechaPublicacion,
      comandoLibro.editorial,
      comandoLibro.autor,
      comandoLibro.paginas)
  }

}
