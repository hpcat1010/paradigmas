package AxiomPackageV3;

public class SondaNotDeployed extends SondaState {
    public void canTurn() {

    }

    @Override
    public boolean isDeployed() {
        return false;
    }
}