package unoV1;

public abstract class Card {


    public abstract String getColor();

    public abstract Object getValue();

    public abstract void effect();

    public abstract Card playMeAs(String red);
}
