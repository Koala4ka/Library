import com.google.inject.AbstractModule
import daos.BookDao
import daos.impl.BookDaoImpl
import monix.execution.Scheduler
import service.BookService
import service.impl.BookServiceImpl

class Module extends AbstractModule {

  override def configure() = {
    bind(classOf[Scheduler]).toInstance(Scheduler.global)
    bind(classOf[BookDao]).to(classOf[BookDaoImpl])
    bind(classOf[BookService]).to(classOf[BookServiceImpl])
  }
}