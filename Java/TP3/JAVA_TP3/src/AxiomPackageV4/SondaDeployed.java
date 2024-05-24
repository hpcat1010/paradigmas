package AxiomPackageV4;

public class SondaDeployed extends SondaControler {
    public void canMove() {
        throw new RuntimeException(Axiom.ErrorSonda);
    }

    @Override
    public void canDeploy() {

    }

    @Override
    public boolean isDeployed() {
        return true;
    }

    @Override
    public void canSlowDown() {
        Axiom.currentSpeed.speedIs1();
    }


    public void deploySonda() {

    }


    public void retractSonda() {
        Axiom.sondaState = new SondaNotDeployed();
    }

    public boolean whatAmI() {
        return true;
    }
}