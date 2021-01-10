package com.joel.akka.actor

import akka.actor.typed.scaladsl.{AbstractBehavior, ActorContext, Behaviors}
import akka.actor.typed.{Behavior, PostStop, Signal, SupervisorStrategy}

import scala.util.Random

/**
 * Device actor protocol including
 * - reading the temperature
 * - respond with the current temperature
 */
object Device {

  def apply(groupId: String, deviceId: String): Behavior[DeviceRequest] = {

    // setup of the device actor defined by the command behavior
    val device: Behavior[DeviceRequest] = Behaviors.setup[DeviceRequest](context => new Device(context, groupId, deviceId))

    // the following default strategy handles all NonFatal exceptions
    //Behaviors.supervise(device).onFailure(SupervisorStrategy.resume)

    // defines the supervision strategy if an IndexOutOfBoundsException is thrown and returns the wrapped behavior
    Behaviors.supervise(device).onFailure[IndexOutOfBoundsException](SupervisorStrategy.resume)

  }


}

/**
 * Device actor protocol implementation.
 *
 * @param context ActorContext which provides access to the Actor's own identitiy, the ActorSystem, child Actors, etc.
 * @param groupId groupId parameter of the Actor
 * @param deviceId deviceId parameter of the Actor
 */
class Device(context: ActorContext[DeviceRequest], groupId: String, deviceId: String) extends AbstractBehavior[DeviceRequest]() {

  var requestNumber: Int = 0

  context.log.info("Device-actor {}-{} started.", groupId, deviceId)

  override def onMessage(msg: DeviceRequest): Behavior[DeviceRequest] = {
    msg match {
      case Readout(id, replyTo) =>
        requestNumber = requestNumber + 1
        if(requestNumber % 5 == 0)
          throw new IndexOutOfBoundsException()
        replyTo ! ReadoutValue(id, Some(Random.between(-30, 50)))
        this
    }
  }

  override def onSignal: PartialFunction[Signal, Behavior[DeviceRequest]] = {
    case PostStop =>
      context.log.info("Device actor {}-{} stopped", groupId, deviceId)
      this
  }

}
