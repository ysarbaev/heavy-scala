package com.sarbaev.heavy

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Flow

import scala.io.StdIn

/**
  * Created by ysarbaev on 12/11/16.
  */
object Heavy extends App {

  implicit val actorSystem = ActorSystem("heavy")
  implicit val actorMaterializer = ActorMaterializer()

  val config = actorSystem.settings.config
  val interface = config.getString("interface")
  val port = config.getInt("port")

  val echo: Route = path("ws-echo") {
    get {
      handleWebSocketMessages {
        Flow[Message].map {
          case TextMessage.Strict(msg) => TextMessage("ECHO " + msg)
          case _ => TextMessage("message unsupported type")
        }
      }
    }
  }

  val route = echo

  val binding = Http().bindAndHandle(route, interface, port)
  println("Server is online, press any key to stop")

  StdIn.readLine()

  import actorSystem.dispatcher

  binding.flatMap(_.unbind()).flatMap(_ => actorSystem.terminate()).onComplete { t =>
    println("Stopped")
  }
}
