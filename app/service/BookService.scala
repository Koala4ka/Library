package service

import models.Book
import monix.eval.Task
import reactivemongo.api.bson.BSONObjectID
import reactivemongo.api.commands.WriteResult


trait BookService {

  def getAll(page: Int, limit: Int): Task[Seq[Book]]

  def getById(id: BSONObjectID): Task[Option[Book]]

  def create(book: Book): Task[Book]

  def update(id: BSONObjectID, book: Book): Task[Option[Book]]

  def delete(id: BSONObjectID): Task[WriteResult]

}
