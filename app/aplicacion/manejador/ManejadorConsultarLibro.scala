package aplicacion.manejador

import dominio.modelo.Libro
import dominio.persistencia.dao.DaoLibro
import javax.inject.Inject

import scala.concurrent.Future

class ManejadorConsultarLibro @Inject() (daoLibro: DaoLibro) {

  def ejecutar(id: String): Future[Option[Libro]] = {
    daoLibro.buscarPorId(id)
  }

}
