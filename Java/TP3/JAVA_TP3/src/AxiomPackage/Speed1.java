package AxiomPackage;

public class Speed1 extends Velocity {
    private final Velocity previous;
    private final int speed;

    public Speed1(Velocity previous, int speed) {
        this.previous = previous;
        this.speed = speed;
    }

    public Velocity speedUp() {
        return new SpeedMore1(previous, speed + 1);
    }

    public Velocity slowDown() {
        return previous;
    }


    public int getSpeed() {
        return speed;
    }

    public void canDeploySonda() {
    }

    @Override
    public void canSlowDown() {
        throw new RuntimeException(Axiom.ErrorSonda);
    }
}
