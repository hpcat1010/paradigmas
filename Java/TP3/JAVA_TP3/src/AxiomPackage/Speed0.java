package AxiomPackage;

public class Speed0 extends VelocityPilot {
    private final VelocityPilot previous = this;
    private final int speed = 0;


    public VelocityPilot speedUp() {
        VelocityPilot next = new Speed1(previous, (speed + 1));
        next.previous = this;
        next.speed = 1;
        return next;
    }

    public VelocityPilot slowDown() {
        return previous;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void canDeploySonda() {
            throw new RuntimeException(Axiom.ErrorSonda);
    }

    @Override
    public void canSlowDown() {

    }


}

