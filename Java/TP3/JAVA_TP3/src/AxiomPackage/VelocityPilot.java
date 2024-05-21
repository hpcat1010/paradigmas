package AxiomPackage;

public abstract class VelocityPilot {
    protected int speed;
    protected VelocityPilot previous;

    public abstract VelocityPilot speedUp();
    public abstract VelocityPilot slowDown();
    public abstract int getSpeed();

    public abstract void canDeploySonda();


    public abstract void canSlowDown() ;
}
