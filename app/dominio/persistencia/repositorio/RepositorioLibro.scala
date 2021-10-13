package dominio.persistencia.repositorio

import dominio.errores.error.ErrorDominio
import dominio.modelo.Libro

import scala.concurrent.Future



trait RepositorioLibro {

  /**
   * Permite insertar un Libro
   *
   * @param libro : Libro
   */
  def insertar(libro: Libro): Future[Option[Libro]]

  /**
   * Permite actualizar un Libro
   *
   * @param libro : Libro
   */
  def actualizar(id: String, libro: Libro): Future[Option[Libro]]

  /**
   * Permite eliminar un Libro
   *
   * @param id : Libro
   */
  def eliminar(id: String): Future[Option[Libro]]

  /**
   * Permite validar si existe un Asignatura con un nombre
   *
   * @param id : Libro
   * @return si existe o no
   */
  def existe(id: String): Future[Boolean]
}
