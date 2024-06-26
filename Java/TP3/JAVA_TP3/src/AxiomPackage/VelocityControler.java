package AxiomPackage;

public abstract class VelocityControler {
    protected VelocityControler previous;

    public static VelocityControler createInitialState() {
        return Axiom.currentSpeed = new Speed0();
    }

    public abstract void speedUp();

    public abstract void slowDown();

    public abstract int getSpeed();

    public abstract void speedIs0();

    public abstract void speedIs1();


}
