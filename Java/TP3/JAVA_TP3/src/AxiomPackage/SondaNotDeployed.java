package AxiomPackage;

public class SondaNotDeployed extends SondaState {
    public void canTurn(Direccions direction) {

    }

    @Override
    public boolean isDeployed() {
        return false;
    }
}