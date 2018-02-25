package it.wext.emailystream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import it.wext.emailystream.api.EmailyStreamService
import it.wext.emaily.api.EmailyService
import com.softwaremill.macwire._

class EmailyStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new EmailyStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new EmailyStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[EmailyStreamService])
}

abstract class EmailyStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[EmailyStreamService](wire[EmailyStreamServiceImpl])

  // Bind the EmailyService client
  lazy val emailyService = serviceClient.implement[EmailyService]
}
