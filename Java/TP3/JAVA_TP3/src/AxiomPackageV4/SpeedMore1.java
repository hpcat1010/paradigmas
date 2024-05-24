package AxiomPackageV4;

public class SpeedMore1 extends VelocityControler {
    private final VelocityControler previous;
    private final int speed;

    public SpeedMore1(VelocityControler previous, int speed) {
        this.previous = previous;
        this.speed = speed;
    }

    public void speedUp() {
        Axiom.currentSpeed = new SpeedMore1(this, speed + 1);
    }

    public void slowDown() {
        Axiom.currentSpeed = previous;
    }


    public int getSpeed() {
        return speed;
    }

    @Override
    public void speedIs0() {

    }

    @Override
    public void speedIs1() {

    }


}
