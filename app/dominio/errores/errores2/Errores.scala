package dominio.errores.errores2

sealed trait Errores

sealed trait Detalle extends Errores {
  val mensaje: String
}

object Errores {

  final case class ValorExistente(mensaje: String) extends Detalle

  def valorExistente(mensaje: String = "elemento existente"): Detalle = ValorExistente(mensaje)

}

