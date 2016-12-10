package com.sarbaev.heavy

import akka.actor.{ActorRef, Actor}
import akka.actor.Actor.Receive

import scala.collection.mutable

/**
 * Created by ysarbaev on 8/3/16.
 */
class FieldActor(x: Int, y: Int, xSize: Int, ySize: Int) extends Actor {

//    private var topNei: Option[ActorRef] = None
//    private var leftNei: Option[ActorRef] = None
//    private var bottNei: Option[ActorRef] = None
//    private var rightNei: Option[ActorRef] = None

    private val field = Array.ofDim[Byte](xSize, ySize)

    private val vehicles = mutable.Map[Int, (Vehicle, Int, Int)]()

    override def receive: Receive = {
        case MoveUp(vid) if vehicles.contains(vid) =>

            val (vehicle, x, y) = vehicles(vid)
            vehicles(vid) = (vehicle, x, y - 1)

        case MoveRight(vid) =>
        case MoveDown(vid) =>
        case MoveLeft(vid) =>
//        case Transfer(vehicle) =>

        case unknown => println("Unknown command :"+unknown)
    }
}
