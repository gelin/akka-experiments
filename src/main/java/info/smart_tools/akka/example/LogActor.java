package info.smart_tools.akka.example;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

public class LogActor extends UntypedActor {

    public static Props props(final String name) {
        return Props.create(new Creator<LogActor>() {
            @Override
            public LogActor create() throws Exception {
                return new LogActor(name);
            }
        });
    }

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private final String name;

    public LogActor(String name) {
        this.name = name;
    }

    public void onReceive(Object message) throws Exception {
        log.info(name + " received\n" + message);
    }

}
