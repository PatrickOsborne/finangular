package org.ozzysoft.finangular.server.http

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class AngularController extends Controller {

  logger.info("created")

  get("/client/lib/:*") { request: Request =>
    response.ok.file("/lib/" + request.params("*"))
  }

  get("/client/:*") { request: Request =>
    response.ok.fileOrIndex(request.params("*"), "/index.html")
  }

  // temporary

  get("/ping:*") { request: Request =>
    logger.debug(s"ping (${request.method}: ${request.path}: ${request.remoteAddress})")
    "pong"
  }

}