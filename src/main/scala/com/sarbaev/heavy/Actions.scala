package com.sarbaev.heavy

import akka.actor.ActorRef

/**
  * Created by ysarbaev on 12/10/16.
  */
object Actions {
  trait Action

  case object Tick extends Action

  case class Join(player: Player, ref: ActorRef) extends Action
  case class Joined(player: Player) extends Action
  case class Leave(pid: Int) extends Action
  case class Left(pid: Int) extends Action
  case class Position(pid: Int, x: Int, y: Int) extends Action


  case class GoUp(pid: Int) extends Action
  case class GoRi(pid: Int) extends Action
  case class GoDo(pid: Int) extends Action
  case class GoLe(pid: Int) extends Action

}
