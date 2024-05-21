package AxiomPackageV2;

public class SondaDeployed extends SondaState{
    public void canTurn(Directions direction) {
        throw new RuntimeException(Axiom.ErrorSonda);
    }

    @Override
    public boolean isDeployed() {
        return true;
    }
}