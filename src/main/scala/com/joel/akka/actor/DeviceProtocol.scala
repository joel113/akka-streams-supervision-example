package com.joel.akka.actor

import akka.actor.typed.ActorRef

sealed trait DeviceRequest
final case class Readout(requestId: Long, replyTo: ActorRef[ReadoutValue]) extends DeviceRequest

sealed trait DeviceResponse
final case class ReadoutValue(requestId: Long, value: Option[Int]) extends DeviceResponse
