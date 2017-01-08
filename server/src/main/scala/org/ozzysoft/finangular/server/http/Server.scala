package org.ozzysoft.finangular.server.http

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.inject.TwitterModule

object ServerMain extends Server {
}

class Server extends HttpServer {
  logger.info("created")

  override val modules = Seq(ServerModule)

  override def configureHttp(router: HttpRouter) {
    logger.info("configure")
    router.add[AngularController]
  }
}

object ServerModule extends TwitterModule {

  protected override def configure(): Unit = {
    logger.info("configure")
    super.configure()
  }
}

