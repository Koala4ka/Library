package service.impl

import daos.BookDao
import models.Book
import monix.eval.Task
import reactivemongo.api.bson.BSONObjectID
import reactivemongo.api.commands.WriteResult
import service.BookService

import javax.inject.Inject

class BookServiceImpl @Inject()(dao: BookDao) extends BookService {

  override def getAll(page: Int, limit: Int): Task[Seq[Book]] = dao.getAll(page, limit)

  override def getById(id: BSONObjectID): Task[Option[Book]] = dao.getById(id)

  override def create(book: Book): Task[Book] = dao.create(book)

  override def update(id: BSONObjectID, book: Book): Task[Option[Book]] = dao.update(id, book)

  override def delete(id: BSONObjectID): Task[WriteResult] = dao.delete(id)
}
