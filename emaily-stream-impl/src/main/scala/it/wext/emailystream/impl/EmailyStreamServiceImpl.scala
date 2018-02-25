package it.wext.emailystream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import it.wext.emailystream.api.EmailyStreamService
import it.wext.emaily.api.EmailyService

import scala.concurrent.Future

/**
  * Implementation of the EmailyStreamService.
  */
class EmailyStreamServiceImpl(emailyService: EmailyService) extends EmailyStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(emailyService.hello(_).invoke()))
  }
}
