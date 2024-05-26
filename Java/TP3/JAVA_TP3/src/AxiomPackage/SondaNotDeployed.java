package AxiomPackage;

public class SondaNotDeployed extends SondaControler {
    public void canMove() {

    }

    @Override
    public void canDeploy() {
        Axiom.currentSpeed.speedIs0();
    }


    public boolean isDeployed() {
        return false;
    }

    @Override
    public void canSlowDown() {

    }


    public void deploySonda() {
        Axiom.sondaState.canDeploy();
        Axiom.sondaState = new SondaDeployed();
    }


    public void retractSonda() {

    }
    public boolean whatAmI(){
        return false;
    }

}