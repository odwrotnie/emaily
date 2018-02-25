package controllers

import javax.inject._

import it.wext.emaily.api.EmailyService
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class Main @Inject()(emailyService: EmailyService,
                     cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index() = Action.async { implicit rh =>
    emailyService.hello("Pawel").invoke() map { x =>
      Ok(views.html.index(x))
    }
  }
}
