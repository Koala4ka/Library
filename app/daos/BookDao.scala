package daos

import models.Book
import monix.eval.Task
import reactivemongo.api.bson.{BSONDocument, BSONObjectID}
import reactivemongo.api.commands.WriteResult


trait BookDao {

  def getAll(page: Int, limit: Int): Task[Seq[BSONDocument]]

  def getById(id: BSONObjectID): Task[Option[BSONDocument]]

  def create(book: Book): Task[Book]

  def update(id: BSONObjectID, book: Book): Task[Option[BSONDocument]]

  def delete(id: BSONObjectID): Task[WriteResult]
}
