package infraestructura.error

case class ExcepcionTecnica(mensaje: String, error: Throwable) extends Exception(mensaje, error)
