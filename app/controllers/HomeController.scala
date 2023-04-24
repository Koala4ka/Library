package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("Hello, world!")
  }

  def hello(name: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(s"Hello, $name!")
  }
}