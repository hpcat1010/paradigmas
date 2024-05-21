package AxiomPackageV2;

public abstract class Velocity {
    protected int speed;
    protected Velocity previous;

    public abstract Velocity speedUp();
    public abstract Velocity slowDown();
    public abstract int getSpeed();

    public abstract void canDeploySonda();


    public abstract void canSlowDown() ;
}