package org.ozzysoft.finangular.server.http

import java.io.InputStream
import java.util.Properties
import java.util.concurrent.atomic.AtomicInteger

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.QueryParam
import org.ozzysoft.finangular.server.common.FileHelper

import scala.collection.JavaConverters._

case class GreetingRequest(@QueryParam name: Option[String], request: Request)

case class Greeting(content: String, count: Int)

case class Version(version: String, buildTimestamp: String, commit: String)

class RestController extends Controller with FileHelper {

  logger.info("created")

  val counter = new AtomicInteger()

  val properties = loadGitProperties()

  get("/rest/version") { request: Request =>
    Version(properties.getProperty("git.build.version", "unknown version"), properties.getProperty("git.build.time", "no build time"),
      properties.getProperty("git.commit.id.abbrev", "no commit"))
  }

  get("/rest/greeting") { request: GreetingRequest =>
    val count = counter.incrementAndGet()
    Greeting(s"Hola Mi Amigo ${request.name.getOrElse("")}", count)
  }

  private def loadGitProperties(): Properties = {
    val stream: InputStream = getResource("git.properties")
    val properties = new Properties()
    properties.load(stream)

    properties.stringPropertyNames().asScala.foreach { p => logger.info(s"property: ($p) -> (${properties.getProperty(p)})") }
    properties
  }

}