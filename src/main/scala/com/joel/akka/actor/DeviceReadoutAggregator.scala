package com.joel.akka.actor

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.{AbstractBehavior, Behaviors}
import com.joel.akka.actor.DeviceReadoutAggregator.aggregate

object DeviceReadoutAggregator {
  def apply() : Behavior[ReadoutValue] = {
    Behaviors.setup[ReadoutValue](_ => new DeviceReadoutAggregator(Map.empty))
  }
  def aggregate(aggregatorMap: Map[Long, Int]) : Behavior[ReadoutValue] = {
    new DeviceReadoutAggregator(aggregatorMap)
  }
}

class DeviceReadoutAggregator(aggregatorMap: Map[Long, Int]) extends AbstractBehavior[ReadoutValue] {

  /**
   * Aggregates the readout values to a immutable.
   * @param msg ReadoutValue to be aggregated.
   * @return Behavior defined on top of the ReadoutValue protocol.
   */
  override def onMessage(msg: ReadoutValue): Behavior[ReadoutValue] = {
    msg match {
      case ReadoutValue(requestId: Long, value: Option[Int]) =>
        println(s"The current aggregated map state is ${aggregatorMap.toString()}.")
        println(s"The new aggregated value of requestId $requestId is $value.")
        aggregate(aggregatorMap + (requestId -> value.getOrElse(0)))
    }
  }

}
