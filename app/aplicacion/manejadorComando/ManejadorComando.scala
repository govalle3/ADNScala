package aplicacion.manejadorComando

trait ManejadorComando[P] {
  def ejecutar(comando: P): Unit
}
