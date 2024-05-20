package AxiomPackage;

public class SpeedNot0 extends Velocity{
    private final Velocity previous;
    private final int speed;

    public SpeedNot0(Velocity previous, int speed) {
        this.previous = previous;
        this.speed = speed;
    }

    public Velocity speedUp() {
        return new SpeedNot0(previous, speed + 1);
    }

    public Velocity slowDown() {
        return previous;
    }


    public int getSpeed() {
        return speed;
    }





}
