package info.smart_tools.akka.example;

import akka.actor.ActorRef;

public interface IMessageMap {

    /**
     *  Returns next actor in the chain.
     *  @param current current actor processing the chain
     */
    ActorRef next(ActorRef current);

}
