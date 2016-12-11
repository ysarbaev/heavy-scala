package com.sarbaev.heavy

import akka.actor.{Actor, ActorRef}
import akka.actor.Actor.Receive
import com.sarbaev.heavy.Actions.{GoDo, GoLe, GoRi, GoUp}

import scala.collection.mutable

/**
  * Created by ysarbaev on 8/3/16.
  */
class FieldActor(xSize: Int, ySize: Int) extends Actor {

  final val UP_DY = -1
  final val DO_DY = 1
  final val RI_DX = 1
  final val LE_DX = -1

  private val field = Array.ofDim[Byte](xSize, ySize)
  private val players = mutable.Map[Int, Player]()

  override def receive: Receive = {

    case GoUp(pid) if players.contains(pid) => {
      val player = players(pid)
      if (checkPosition(player, 0, UP_DY)) {
        player.y += UP_DY
      }
    }

    case GoRi(pid) if players.contains(pid) => {
      val player = players(pid)
      if (checkPosition(player, RI_DX, 0)) {
        player.x += RI_DX
      }
    }

    case GoDo(pid) if players.contains(pid) => {
      val player = players(pid)
      if (checkPosition(player, 0, DO_DY)) {
        player.y += DO_DY
      }
    }
    case GoLe(pid) if players.contains(pid) => {
      val player = players(pid)
      if (checkPosition(player, LE_DX, 0)) {
        player.x += LE_DX
      }
    }

    case unknown => println("Unknown command :" + unknown)
  }

  //TODO
  def checkPosition(player: Player,
    dx: Int,
    dy: Int): Boolean = {
    true
  }
}
