package com.joel.akka.http

import scala.concurrent.Future

trait VehicleOperations {
  def insertVehicle(vehicle: Vehicle) : Future[Vehicle]
  def updateVehicle(vehicle: Vehicle) : Future[Vehicle]
  def getVehicle(vin: String) : Future[Vehicle]
}
