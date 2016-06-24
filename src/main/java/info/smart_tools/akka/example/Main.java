package info.smart_tools.akka.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");

        List<ActorRef> targets = new ArrayList<ActorRef>();
        targets.add(system.actorOf(SampleActor.props("First"), "first"));
        targets.add(system.actorOf(SampleActor.props("Second"), "second"));
        targets.add(system.actorOf(SampleActor.props("Third"), "third"));
        IMessageMap messageMap = new StraightMessageMap(targets);

        ActorRef logActor = system.actorOf(LogActor.props("Logger"), "logger");

        ActorRef map = system.actorOf(MessageMapActor.props(messageMap, logActor), "messageMap");
        IText initialMessage = new SampleMessage("initial");
        map.tell(initialMessage, logActor); // run once
        map.tell(initialMessage, logActor); // run second
    }

}
