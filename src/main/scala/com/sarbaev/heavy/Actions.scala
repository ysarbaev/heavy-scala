package com.sarbaev.heavy

/**
  * Created by ysarbaev on 12/10/16.
  */
object Actions {
  case object Tick

  case class GoUp(pid: Int)
  case class GoRi(pid: Int)
  case class GoDo(pid: Int)
  case class GoLe(pid: Int)

}
