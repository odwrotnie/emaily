package controllers

import javax.inject._

import it.wext.emaily.api.EmailyService
import play.api.mvc._

@Singleton
class Main @Inject()(emailyService: EmailyService,
                     cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("asdf"))
  }
}
