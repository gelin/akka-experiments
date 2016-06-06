package info.smart_tools.akka.example;

import akka.actor.ActorRef;

import java.util.List;

public class StraightMessageMap implements IMessageMap {

    private final List<ActorRef> targets;
    private int currentIndex;

    public StraightMessageMap(List<ActorRef> targets) {
        this.targets = targets;
        this.currentIndex = 0;
    }

    public ActorRef next() {
        if (currentIndex < targets.size()) {
            return targets.get(currentIndex++);
        } else {
            return null;    // is this the good indicator for the map end?
        }
    }

}
