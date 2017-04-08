package org.ozzysoft.finangular.server.http

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.QueryParam

case class GreetingRequest(@QueryParam name: Option[String], request: Request)

case class Greeting(content: String)

case class Version(version: String, buildTimestamp: String, commit: String)

class RestController extends Controller {

  logger.info("created")

  get("/rest/version") { request: Request =>
    Version("my version", "my build timestamp", "my commit")
  }

  get("/rest/greeting") { request: GreetingRequest =>
    Greeting(s"Hola Mi Amigo ${request.name.getOrElse("")}")
  }

}