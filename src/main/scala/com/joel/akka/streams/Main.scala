package com.joel.akka.streams

import akka.actor.ActorSystem
import akka.stream.{ActorAttributes, Supervision}
import akka.stream.scaladsl.{Sink, Source}

object Main {

  val decider1: Supervision.Decider = {
    case _ => {
      println("Feteched exception 1.")
      Supervision.Resume
    }
  }

  val decider2: Supervision.Decider = {
    case _ => {
      println("Feteched exception 2.")
      Supervision.Resume
    }
  }

  def main(args: Array[String]): Unit = {
    // initializes the actor system and initializes the main actor
    // the main actor is also called the guardian actor
    implicit val system : ActorSystem = ActorSystem("actor-system")

    val stream = Source(0 to 5)
      .withAttributes(ActorAttributes.supervisionStrategy(decider1))
      .map(_ / 0)
      .map(_ + 1)
      .withAttributes(ActorAttributes.supervisionStrategy(decider2))
      .to(Sink.foreach(println(_))).run()

  }

}
