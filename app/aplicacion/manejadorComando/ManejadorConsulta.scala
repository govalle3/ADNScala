package aplicacion.manejadorComando

import aplicacion.manejadorComando.Resultado.Respuesta

trait ManejadorConsulta[R] {
  def ejecutar(): Respuesta[R]
}
