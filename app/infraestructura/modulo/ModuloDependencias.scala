package infraestructura.modulo

import com.google.inject.AbstractModule
import dominio.persistencia.dao.DaoLibro
import dominio.persistencia.repositorio.RepositorioLibro
import infraestructura.persistencia.dao.DaoLibroImpl
import infraestructura.persistencia.repositorio.RepositorioLibroImpl

class ModuloDependencias extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[RepositorioLibro]).to(classOf[RepositorioLibroImpl]).asEagerSingleton()
    bind(classOf[DaoLibro]).to(classOf[DaoLibroImpl]).asEagerSingleton()
  }

}
