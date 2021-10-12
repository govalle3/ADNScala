package aplicacion.manejadorComando

import aplicacion.manejadorComando.Resultado.Respuesta

trait ManejadorConsultaParametro[P, R] {
  def ejecutar(comando: P): Respuesta[R]
}

