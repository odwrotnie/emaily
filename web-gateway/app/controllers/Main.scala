package controllers

import javax.inject._

import play.api.mvc._

@Singleton
class Main @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
