package com.joel.akka.actor

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior, Props, SupervisorStrategy, Terminated}
import akka.util.Timeout
import com.joel.akka.actor.DeviceReadoutAggregator.aggregate

import scala.concurrent.duration.DurationInt

object Main {

  def apply() : Behavior[NotUsed] =
    Behaviors.setup[NotUsed] { context =>

      val device1 = context.spawn(Device("group-1", "device-1"), "device")

      val deviceReadoutAggregator = context.spawn(DeviceReadoutAggregator(), "device-readout-aggregator")

      // tell-pattern approach for communication between actors
      val r = 1 to 100
      r.foreach(device1 ! Readout(_, deviceReadoutAggregator))

      // ask-pattern approach for communication between actors
//      implicit val timeout: Timeout = 3.seconds
//      context.ask(device1, Readout) {
//        case ReadoutValue(requestId: Long, value: Option[Int]) =>
//          println(s"The answer to the ask is $value.")
//      }

      Behaviors.receiveSignal{
        case (_, Terminated(_)) =>
          Behaviors.stopped
      }
    }

  def main(args: Array[String]): Unit = {
    // initializes the actor system and initializes the main actor
    // the main actor is also called the guardian actor
    val system : ActorSystem[NotUsed] = ActorSystem[NotUsed](Main(), "actor-system")
  }

}
