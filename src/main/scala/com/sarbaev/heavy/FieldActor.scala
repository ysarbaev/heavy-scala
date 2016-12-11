package com.sarbaev.heavy

import akka.actor.{Actor, ActorRef}
import akka.actor.Actor.Receive
import com.sarbaev.heavy.Actions._

import scala.collection.mutable

/**
  * Created by ysarbaev on 8/3/16.
  */
class FieldActor(xSize: Int, ySize: Int) extends Actor {

  final val UP_DY = -1
  final val DO_DY = 1
  final val RI_DX = 1
  final val LE_DX = -1

  final val FREE_TILE = -1

  val rnd = new java.util.Random()

  class Location(var x: Int, var y: Int)

  private val field = Array.ofDim[Int](xSize, ySize)
  private val players = mutable.Map[Int, (Location, ActorRef)]()

  def broadcast(action: Action) = players.values.foreach {
    case (_, ref) => ref ! action
  }

  override def receive: Receive = {

    case GoUp(pid) if players.contains(pid) => {
      val (player, _) = players(pid)
      if (checkPosition(player, 0, UP_DY)) {
        player.y += UP_DY
      }
    }

    case GoRi(pid) if players.contains(pid) => {
      val (player, _) = players(pid)
      if (checkPosition(player, RI_DX, 0)) {
        player.x += RI_DX
      }
    }

    case GoDo(pid) if players.contains(pid) => {
      val (player, _) = players(pid)
      if (checkPosition(player, 0, DO_DY)) {
        player.y += DO_DY
      }
    }
    case GoLe(pid) if players.contains(pid) => {
      val (player, _) = players(pid)
      if (checkPosition(player, LE_DX, 0)) {
        player.x += LE_DX
      }
    }

    case Join(player, ref) => {
      val loc = freeLocation
      players += player.pid -> (loc, ref)
      field(loc.x)(loc.y) = player.pid
      broadcast(Joined(player))
    }

    case c@Leave(pid) => {
      players.remove(pid).foreach {
        case (loc, _) =>
          field(loc.x)(loc.y) == FREE_TILE
          broadcast(Left(pid))
          sender() ! Left(pid)
      }
    }

    case unknown => println("Unknown command :" + unknown)
  }

  //TODO
  def checkPosition(loc: Location, dx: Int, dy: Int): Boolean = {
    true
  }

  def freeLocation: Location = {
    val x = rnd.nextInt(xSize)
    val y = rnd.nextInt(ySize)

    val pid = field(x)(y)
    if (pid == FREE_TILE) {
      new Location(x, y)
    } else {
      freeLocation
    }
  }

}
