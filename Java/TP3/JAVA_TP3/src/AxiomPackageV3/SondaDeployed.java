package AxiomPackageV3;

public class SondaDeployed extends SondaState{
    public void canTurn() {
        throw new RuntimeException(Axiom.ErrorSonda);
    }

    @Override
    public boolean isDeployed() {
        return true;
    }
}