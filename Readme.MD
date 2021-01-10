# Akka Supervision Example

This example project is about supervising actor in Akka. Supervision means to oversee actors for failure handling.
Supervising actors oversee actors in an hierarchy of actors. There are different possible actions: resume, restart,
start or escalate.

Please, be aware that there are two Akka Actor implementations:

- classic
- typed

You can find more information about Akka Actor Classic using the following link:

https://doc.akka.io/docs/akka/current/actors.html

Whereas, using the following link leads to more information about the new Akka Actor Typed implementation:

https://doc.akka.io/docs/akka/current/typed/actors.html

This example project uses the new Akka Actor Typed implementation. Please check the following subsection for more
information about the actor supervision with Akka Actor Classic.

## Akka Actor Typed

Akka Actor Type is the latest Akka implementation of the actor model. An actor is defined based on the behavior which is
defined on top of the protocol.

https://doc.akka.io/docs/akka/current/typed/actors.html

The Actor implementation is called "typed" as the behavior implementation is defined on top of the type variable t which
defines the protocol:

`Behavior[-T]`

If the behavior of the Device Actor is defined using

`Behavior[DeviceRequest]`

the behavior is defined on top of the DeviceRequest protocol.

Please check the following introductions into Akka Actor Typed:

https://www.baeldung.com/scala/typed-akka

https://alvinalexander.com/scala/how-to-start-akka-actors-in-scala/

Please check the following classes of the Akka Actor Typed Scala DSL implementation:

https://doc.akka.io/api/akka/current/akka/actor/typed/scaladsl/AbstractBehavior.html

https://doc.akka.io/api/akka/current/akka/actor/typed/scaladsl/ActorContext.html

https://doc.akka.io/docs/akka/current/typed/from-classic.html

## Akka Actor Classic Supervision

https://doc.akka.io/docs/akka/current/general/supervision.html

https://doc.akka.io/docs/akka/current/typed/guide/tutorial_2.html

https://doc.akka.io/docs/akka/current/typed/guide/tutorial_3.html

https://doc.akka.io/docs/akka/current/typed/guide/modules.html

https://medium.com/akka-for-newbies/actor-supervision-dbbb96734176

https://blog.knoldus.com/supervising-actors-in-akka/

## Akka Actor Typed Supervision

The following fault tolerance subsection of the Akka Actor Typed documentation introduces the concept of actor
supervision with the Akka Actor Typed API:

https://doc.akka.io/docs/akka/current/typed/fault-tolerance.html#supervision

To use actor supervision, the typed behavior is wrapped with the supervision behavior. The following line of code wrapps
the behavior with a supervision behavior if an IllegalStateException occurs:

`Behaviors.supervise(behavior).onFailure[IllegalStateException](SupervisorStrategy.restart)`

The documentation introduces more advanced means when it comes to define supervision behavior:

`Behaviors
.supervise(behavior)
.onFailure[IllegalStateException](
SupervisorStrategy.restart.withLimit(maxNrOfRetries = 10, withinTimeRange = 10.seconds))`

Furthermore, it is possible to nest supervision behavior within supervision behavior:

`Behaviors
.supervise(Behaviors.supervise(behavior).onFailure[IllegalStateException](SupervisorStrategy.restart))
.onFailure[IllegalArgumentException](SupervisorStrategy.stop)`

The full source code of the examples of the documentation can be found here:

https://github.com/akka/akka/blob/v2.6.10/akka-actor-typed-tests/src/test/scala/docs/akka/typed/supervision/SupervisionCompileOnly.scala#L36-L38

A full list of strategies can be found here:

https://doc.akka.io/api/akka/2.6/akka/actor/typed/SupervisorStrategy.html

## Akka Actor Typed Testing

https://doc.akka.io/docs/akka/current/typed/testing.html
