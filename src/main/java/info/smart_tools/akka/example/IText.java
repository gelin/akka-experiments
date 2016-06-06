package info.smart_tools.akka.example;

public interface IText {

    String getText();

    /**
     *  Returns the new instance of the object with the modified text value.
     */
    IText setText(String text);

}
