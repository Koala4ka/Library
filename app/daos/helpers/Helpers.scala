package daos.helpers

import controllers.utils.controllers.utils.Exceptions._
import monix.eval.Task

import scala.concurrent.Future

object Helpers {

  implicit class MonixDB[T](val f: Future[T]) extends AnyVal {

    def wrapEx: Task[T] = Task.fromFuture(f).onErrorRecover {
      case DbResultException => throw DbResultException
      case ex => throw DbInternalException(ex)
    }
  }
}
