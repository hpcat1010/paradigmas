package AxiomPackageV2;

public class SpeedMore1 extends VelocityPilot {
    private final VelocityPilot previous;
    private final int speed;

    public SpeedMore1(VelocityPilot previous, int speed) {
        this.previous = previous;
        this.speed = speed;
    }

    public VelocityPilot speedUp() {
        return new SpeedMore1(previous, speed + 1);
    }

    public VelocityPilot slowDown() {
        return previous;
    }


    public int getSpeed() {
        return speed;
    }

    public void canDeploySonda() {

    }
    @Override
    public void canSlowDown() {
    }


}
