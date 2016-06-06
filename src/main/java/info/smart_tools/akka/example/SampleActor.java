package info.smart_tools.akka.example;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

public class SampleActor extends UntypedActor {

    public static Props props(final String name) {
        return Props.create(new Creator<SampleActor>() {
            @Override
            public SampleActor create() throws Exception {
                return new SampleActor(name);
            }
        });
    }

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private final String name;

    public SampleActor(String name) {
        this.name = name;
    }

    public void onReceive(Object message) throws Exception {
        log.info(name + " received " + message);
        if (message instanceof IText) {
            IText text = (IText) message;
            IText reply = text.setText(text.getText() + "\nprocessed by " + name);
            getSender().tell(reply, getSelf());
        } else {
            unhandled(message);
        }
    }

}
