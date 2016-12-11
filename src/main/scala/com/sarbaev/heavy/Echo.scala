package com.sarbaev.heavy

import akka.http.scaladsl.model.ws.Message
import akka.http.scaladsl.model.ws.TextMessage
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.stream.scaladsl.Flow
/**
  * Created by ysarbaev on 12/11/16.
  */
object Echo {

  val route: Route = path("ws-echo") {
    get {
      handleWebSocketMessages(echoService)
    }
  }

  val echoService: Flow[Message, Message, _] = Flow[Message].map {
    case TextMessage.Strict(msg) => TextMessage("ECHO" + msg)
    case _ => TextMessage("message unsupported type")
  }

}
