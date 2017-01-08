package org.ozzysoft.finangular.server.http

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class AngularController extends Controller {

  logger.info("created")

  get("/ping") { request: Request =>
    "pong"
  }

}
