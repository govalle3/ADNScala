package aplicacion.manejador

import dominio.modelo.Libro
import dominio.persistencia.dao.DaoLibro
import javax.inject.Inject

import scala.concurrent.Future

class ManejadorListarLibro @Inject() (daoLibro: DaoLibro) {

  def ejecutar(): Future[Seq[Libro]] = {
    daoLibro.listar
  }

}
