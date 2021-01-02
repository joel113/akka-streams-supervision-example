package com.joel.akka.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

import scala.util.{Failure, Success}

class VehicleRoutes(vehicleOps: VehicleOperations) extends Directives with SprayJsonSupport {

  // uses json-spray jsonFormat2 method for json to case class translation
  implicit val vehicleJsonFormat: RootJsonFormat[Vehicle] = jsonFormat2(Vehicle)

  def routes = concat(
    post {
      path("add-vehicle") {
        entity(as[Vehicle]) {
          vehicle => {
            onComplete(vehicleOps.insertVehicle(vehicle)) {
              case Success(vehicle) => complete(vehicle)
              case Failure(exception) =>
                exception.printStackTrace()
                complete(StatusCodes.InternalServerError)
            }
          }
        }
      }
    },
    post {
      path("update-vehicle") {
        // as supports unmarshalling JSON to Vehicle using the Akka Http Json Support
        entity(as[Vehicle]) {
          vehicle => {
            onComplete(vehicleOps.insertVehicle(vehicle)) {
              case Success(vehicle) => complete(vehicle)
              case Failure(exception) =>
                exception.printStackTrace()
                complete(StatusCodes.InternalServerError)
            }
          }
        }
      }
    },
    get {
      path("get-vehicle") {
        parameter("vin".as[String]) {
          vin => {
            onComplete(vehicleOps.getVehicle(vin)) {
              case Success(vehicle) => complete(vehicle)
              case Failure(_) =>
                complete(StatusCodes.BadRequest)
            }
          }
        }
      }
    },
    get {
      complete("Akka HTTP example.")
    }
  )

}
