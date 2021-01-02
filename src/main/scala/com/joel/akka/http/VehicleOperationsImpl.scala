package com.joel.akka.http
import scala.collection.mutable
import scala.concurrent.{ExecutionContextExecutor, Future}

class VehicleOperationsImpl(implicit executionContext: ExecutionContextExecutor) extends VehicleOperations {

  // a scala mutable map has unique keys
  val vehicleMap : mutable.Map[String, Vehicle] = scala.collection.mutable.Map[String, Vehicle]()

  override def insertVehicle(vehicle: Vehicle): Future[Vehicle] = Future {
    vehicleMap += (vehicle.vin -> vehicle)
    vehicleMap(vehicle.vin)
  }

  override def updateVehicle(vehicle: Vehicle): Future[Vehicle] = Future {
    vehicleMap.update(vehicle.vin, vehicle)
    vehicleMap(vehicle.vin)
  }

  override def getVehicle(vin: String): Future[Vehicle] = Future {
    vehicleMap(vin)
  }

}
