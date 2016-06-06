package info.smart_tools.akka.example;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

/**
 * Created by gelin on 6/6/16.
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

    public MessageMapActor(IMessageMap messageMap) {
        this.messageMap = messageMap;
        //TODO: how to put the message map into the message?
    }

    public void onReceive(Object message) throws Exception {
        ActorRef next = messageMap.next();
        if (null != next) {
            next.tell(message, getSelf());
        } else {
            getContext().stop(getSelf());
        }
    }

}
