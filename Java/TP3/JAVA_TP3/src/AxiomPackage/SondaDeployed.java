package AxiomPackage;

public class SondaDeployed extends SondaState{
    public void canTurn(Direccions direction) {
        throw new RuntimeException(Axiom.ErrorSonda);
    }

    @Override
    public boolean isDeployed() {
        return true;
    }
}