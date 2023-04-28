package controllers.utils

package controllers.utils

object Exceptions {

  case class DbInternalException(e: Throwable) extends RuntimeException("DB exception", e)

  case object DbResultException extends RuntimeException("No raws was affected")

}
