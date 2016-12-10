package com.sarbaev.heavy

import akka.actor.ActorRef

/**
 * Created by ysarbaev on 8/3/16.
 */


//Actions
case object Tick

case class MoveUp(vid: Int)
case class MoveRight(vid: Int)
case class MoveDown(vid: Int)
case class MoveLeft(vid: Int)
case class Transfer(vehicle: Vehicle)

case class LeftNei(ref: ActorRef)
case class RightNei(ref: ActorRef)
case class BottomNei(ref: ActorRef)
case class TopNei(ref: ActorRef)

//Models
case class Vehicle(vid: Int, speed: Int, health: Int)