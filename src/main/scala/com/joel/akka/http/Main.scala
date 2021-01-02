package com.joel.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object Main extends App {

  implicit val system : ActorSystem = ActorSystem("actor-system")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val vehicleOps = new VehicleOperationsImpl()
  val routes = new VehicleRoutes(vehicleOps).routes

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

  bindingFuture.onComplete {
    case Success(done) =>
      println("Server started.")
      println("Press enter to stop.")
      scala.io.StdIn.readLine()
      system.terminate()
    case Failure(exception) =>
      exception.printStackTrace()
      system.terminate()
  }

}
