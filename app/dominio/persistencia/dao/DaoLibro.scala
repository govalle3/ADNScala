package dominio.persistencia.dao

import dominio.modelo.Libro

import scala.concurrent.Future

trait DaoLibro {

  /**
   * Permite listar Libro
   *
   * @return los Libro
   */
  def listar: Future[Seq[Libro]]

  /**
   * permite consultar Libro por id
   * @param id Libro
   * @return Libro
   */
  def buscarPorId(id: String): Future[Option[Libro]]

}