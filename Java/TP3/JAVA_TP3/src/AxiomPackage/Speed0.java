package AxiomPackage;

public class Speed0 extends VelocityControler {


    public void  speedUp() {
        Axiom.currentSpeed = new Speed1(previous, (1));
    }

    public void  slowDown() {

    }

    @Override
    public int getSpeed() {
        return 0;
    }


    public void speedIs0() {
        throw new RuntimeException(Axiom.ErrorSonda);
    }

    public void speedIs1() {

    }
}

