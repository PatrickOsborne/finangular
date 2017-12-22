package org.ozzysoft.finangular.server.http

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.json.modules.FinatraJacksonModule
import com.twitter.inject.TwitterModule

object ServerMain extends Server {
}

class Server extends HttpServer {
  logger.info("created")

  override def jacksonModule = CustomJacksonModule

  override val modules = Seq(ServerModule)

  override def configureHttp(router: HttpRouter) {
    logger.info("configure")
    router.filter[CommonFilters]
    router.add[AngularController]
    router.add[RestController]
  }
}

object ServerModule extends TwitterModule {

  protected override def configure(): Unit = {
    logger.info("configure")
    super.configure()
  }
}

object CustomJacksonModule extends FinatraJacksonModule {

  override val propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE

}
