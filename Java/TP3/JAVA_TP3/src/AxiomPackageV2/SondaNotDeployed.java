package AxiomPackageV2;

public class SondaNotDeployed extends SondaState {
    public void canTurn(Directions direction) {

    }

    @Override
    public boolean isDeployed() {
        return false;
    }
}