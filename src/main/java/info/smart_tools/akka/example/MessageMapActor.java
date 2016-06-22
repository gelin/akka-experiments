package info.smart_tools.akka.example;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

/**
 *  Simple message map actor.
 *  Works like a router to pass a message returned by the actor from the map to the next actor in the map.
 */
public class MessageMapActor extends UntypedActor {

    public static Props props(final IMessageMap messageMap) {
        return Props.create(new Creator<MessageMapActor>() {
            @Override
            public MessageMapActor create() throws Exception {
                return new MessageMapActor(messageMap);
            }
        });
    }

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private final IMessageMap messageMap;
    private ActorRef initialSender = null;

    public MessageMapActor(IMessageMap messageMap) {
        this.messageMap = messageMap;
        //TODO: how to put the message map into the message?
    }

    public void onReceive(Object message) throws Exception {
        if (initialSender == null) {
            initialSender = getSender();
        }
        ActorRef next = messageMap.next(getSender());
        if (null != next) {
            next.tell(message, getSelf());
        } else {
            initialSender.tell(message, getSelf());
            initialSender = null;
        }
    }

}
