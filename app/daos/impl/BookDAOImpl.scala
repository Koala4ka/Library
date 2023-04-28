package daos.impl

import controllers.utils.controllers.utils.Exceptions.DbResultException
import daos.BookDao
import daos.helpers.Helpers.MonixDB
import models.Book
import monix.eval.Task
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocument, BSONObjectID}
import reactivemongo.api.commands.WriteResult

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext


@Singleton
class BookDaoImpl @Inject()(
                             implicit executionContext: ExecutionContext,
                             reactiveMongoApi: ReactiveMongoApi
                           ) extends BookDao {
  def collection: Task[BSONCollection] = reactiveMongoApi.database.map(db => db.collection("my-books")).wrapEx


  import reactivemongo.play.json.compat
  import compat.json2bson._

  def getAll(page: Int, limit: Int): Task[Seq[BSONDocument]] = {
    val offset = (page - 1) * limit
    collection.flatMap {
      _.find(BSONDocument.empty)
        .skip(offset)
        .cursor[BSONDocument]()
        .collect[Seq](limit, Cursor.FailOnError[Seq[BSONDocument]]())
        .wrapEx
    }
  }


  override def create(book: Book): Task[Book] = collection.flatMap {
    _.insert.one(book).map(_.code).wrapEx.flatMap {
      case None => Task.now(book)
      case Some(errCode) => Task.raiseError(DbResultException)
    }
  }

  override def getById(id: BSONObjectID): Task[Option[BSONDocument]] = {
    collection.flatMap(_.find(BSONDocument("_id" -> id), Option.empty[BSONDocument]).one[BSONDocument].wrapEx)
  }


  override def update(id: BSONObjectID, book: Book): Task[Option[BSONDocument]] = {
    val selector = BSONDocument("_id" -> id)
    val update = BSONDocument("$set" -> book)
    for {
      result <- collection.flatMap(_.update.one(selector, update).wrapEx)
      updatedBook <- result match {
        case res if res.n == 0 => Task.now(None)
        case _ => getById(id)
      }
    } yield updatedBook
  }

  def delete(id: BSONObjectID): Task[WriteResult] = {
    collection.flatMap(
      _.delete().one(BSONDocument("_id" -> id), Some(1)).wrapEx
    )
  }
}
