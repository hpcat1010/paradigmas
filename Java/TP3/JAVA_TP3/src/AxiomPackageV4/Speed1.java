package AxiomPackageV4;

public class Speed1 extends VelocityControler {

    public Speed1(VelocityControler previous, int speed) {
        this.previous = previous;

    }

    public void speedUp() {
        Axiom.currentSpeed=  new SpeedMore1(this, 2);
    }

    public void slowDown() {
        Axiom.sondaState.canSlowDown();
        Axiom.currentSpeed = new Speed0();
    }


    public int getSpeed() {
        return 1;
    }

    @Override
    public void speedIs0() {

    }

    @Override
    public void speedIs1() {
        throw new RuntimeException(Axiom.ErrorSonda);
    }


}
