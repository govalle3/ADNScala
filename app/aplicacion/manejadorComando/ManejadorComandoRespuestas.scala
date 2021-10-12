package aplicacion.manejadorComando

trait ManejadorComandoRespuestas[P, R] {
  def ejecutar(comando: P): RespuestaComando[R]
}
