package info.smart_tools.akka.example;

import akka.actor.ActorRef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StraightMessageMap implements IMessageMap {

    private final ActorRef firstTarget;
    private final Map<ActorRef, ActorRef> nextTargets = new HashMap<ActorRef, ActorRef>();

    public StraightMessageMap(List<ActorRef> targets) {
        firstTarget = targets.get(0);
        for (int i = 0; i < targets.size() - 1; i++) {
            nextTargets.put(targets.get(i), targets.get(i + 1));
        }
        nextTargets.put(targets.get(targets.size() - 1), null);
    }

    public ActorRef next(ActorRef current) {
        ActorRef next = nextTargets.get(current);
        if (next == null) {
            if (nextTargets.containsKey(current)) {
                return null;        // the end of chain
            } else {
                return firstTarget; // enter to chain from another actor
            }
        }
        return next;
    }

}
