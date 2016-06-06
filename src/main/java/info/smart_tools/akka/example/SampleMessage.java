package info.smart_tools.akka.example;

public class SampleMessage implements IText {

    private String text;

    public SampleMessage(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public IText setText(String text) {
        return new SampleMessage(text);
    }

    @Override
    public String toString() {
        return String.valueOf(text);
    }
}
