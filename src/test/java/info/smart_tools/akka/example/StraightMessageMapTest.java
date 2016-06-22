package info.smart_tools.akka.example;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class StraightMessageMapTest {

    private ActorSystem actorSystem;
    private ActorRef initialActor;

    @Before
    public void setUp() {
        actorSystem = ActorSystem.create("test");
        initialActor = actorSystem.actorFor("initial");
    }

    @Test(expected = Exception.class)
    public void testZeroTargets() {
        List<ActorRef> targets = new ArrayList<ActorRef>();
        IMessageMap map = new StraightMessageMap(targets);
    }

    @Test
    public void testOneTarget() {
        List<ActorRef> targets = new ArrayList<ActorRef>();
        ActorRef actor1 = actorSystem.actorFor("actor1");
        targets.add(actor1);
        IMessageMap map = new StraightMessageMap(targets);
        assertSame(actor1, map.next(initialActor));
        assertNull(map.next(actor1));
    }

    @Test
    public void testTwoTargets() {
        List<ActorRef> targets = new ArrayList<ActorRef>();
        ActorRef actor1 = actorSystem.actorFor("actor1");
        targets.add(actor1);
        ActorRef actor2 = actorSystem.actorFor("actor2");
        targets.add(actor2);
        IMessageMap map = new StraightMessageMap(targets);
        assertSame(actor1, map.next(initialActor));
        assertSame(actor2, map.next(actor1));
        assertNull(map.next(actor2));
    }

}
