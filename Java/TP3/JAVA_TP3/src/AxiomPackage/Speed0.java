package AxiomPackage;

public class Speed0 extends Velocity {
    private Velocity next;

    @Override
    public Velocity speedUp() {
        next = new SpeedNot0(previous);
        next.previous = this;
        next.speed = 1;
        return next;
    }

    public Velocity slowDown() {
        return this;
    }

    @Override
    public int getSpeed() {
        return 0;
    }







}

