package models

import play.api.libs.json.{Format, Json}
import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

case class Book(
                 title: String,
                 author: String
               )

object Book {
  implicit val format: Format[Book] = Json.format[Book]
  implicit val writer: BSONDocumentWriter[Book] = Macros.writer[Book]
  implicit val reader: BSONDocumentReader[Book] = Macros.reader[Book]

}
