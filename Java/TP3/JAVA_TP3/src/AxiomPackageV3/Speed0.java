package AxiomPackageV3;

public class Speed0 extends VelocityPilot {
    private final VelocityPilot previous = this;
    private final int speed = 0;


    public VelocityPilot speedUp() {
        return new Speed1(previous, (1));
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

