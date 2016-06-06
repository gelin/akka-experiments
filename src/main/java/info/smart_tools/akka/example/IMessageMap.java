package info.smart_tools.akka.example;

import akka.actor.ActorRef;

public interface IMessageMap {

    /**
     *  Returns next actor in the chain.
     */
    ActorRef next();

}
