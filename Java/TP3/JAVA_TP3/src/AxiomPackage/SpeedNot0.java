package AxiomPackage;

public class SpeedNot0 extends Velocity{
    private Velocity next;
    private Velocity previous;
    private int speed;

    public SpeedNot0(Velocity previous) {
        this.previous = previous;
        this.speed = speed;
    }

    @Override
    public Velocity speedUp() {
        next = new SpeedNot0(previous);
        next.previous = this;
        next.speed = speed + 1;
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
