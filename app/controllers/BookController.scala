package controllers

import models.Book
import monix.execution.Scheduler
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import reactivemongo.api.bson.BSONObjectID
import reactivemongo.play.json.compat.bson2json.fromDocumentWriter
import service.BookService

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


@Singleton
class BookController @Inject()(cc: ControllerComponents,
                               bookService: BookService)(implicit ex: ExecutionContext, sch: Scheduler)
  extends AbstractController(cc) {


  def getAll(): Action[AnyContent] = Action.async { request =>
    val page = request.getQueryString("page").map(_.toInt).getOrElse(1)
    val limit = request.getQueryString("limit").map(_.toInt).getOrElse(5)
    val maxLimit = 10
    val clampedLimit = if (limit > maxLimit) maxLimit else limit
    val task = for {
      books <- bookService.getAll(page, clampedLimit)
    } yield Ok(Json.toJson(books))
    task.onErrorHandle {
      case e: Exception => InternalServerError(e.getMessage)
    }.runToFuture
  }

  def create(): Action[Book] = Action.async(parse.json[Book]) { request =>
    val book = request.body
    bookService.create(book).map { createdBook =>
      Created(Json.toJson(createdBook))
    }.onErrorHandle {
      case _: IllegalArgumentException =>
        BadRequest("Invalid request body")
      case _: Exception =>
        InternalServerError("Failed to create book")
    }.runToFuture
  }

  def getById(id: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => bookService.getById(objectId).map {
        book => Ok(Json.toJson(book))
      }.runToFuture
      case Failure(_) => Future.successful(BadRequest("Cannot parse the movie id"))
    }
  }

  def update(id: String): Action[JsValue] = Action.async(controllerComponents.parsers.json) { implicit request => {
    request.body.validate[Book].fold(
      _ => Future.successful(BadRequest("Cannot parse request body")),
      book => {
        val objectIdTryResult = BSONObjectID.parse(id)
        objectIdTryResult match {
          case Success(objectId) => bookService.update(objectId, book).map {
            _ => Ok(Json.toJson(book))
          }.runToFuture
          case Failure(_) => Future.successful(BadRequest("Cannot parse the book id"))
        }
      }
    )
  }
  }

  def delete(id: String): Action[AnyContent] = Action.async { implicit request => {
    val objectIdTryResult = BSONObjectID.parse(id)
    objectIdTryResult match {
      case Success(objectId) => bookService.delete(objectId).map {
        _ => NoContent
      }.runToFuture
      case Failure(_) => Future.successful(BadRequest("Cannot parse the book id"))
    }
  }
  }
}




