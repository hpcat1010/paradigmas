package AxiomPackage;

public class Speed0 extends Velocity {
    private final Velocity previous = this;
    private final int speed = 0;


    public Velocity speedUp() {
        Velocity next = new SpeedNot0(previous, (speed + 1));
        next.previous = this;
        next.speed = 1;
        return next;
    }

    public Velocity slowDown() {
        return previous;
    }

    @Override
    public int getSpeed() {
        return speed;
    }







}

