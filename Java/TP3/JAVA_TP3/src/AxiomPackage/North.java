package AxiomPackage;

public class North extends Direccions {
    @Override
    public Direccions turnRight() {
        return new East();
    }

    @Override
    public Direccions turnLeft() {
        return new West();
    }

    @Override
    public String getDirection() {
        return "North";
    }

    @Override
    public void canTurn(Axiom axiom) {
    if (axiom.currentSonda()) {
            throw new RuntimeException(Axiom.ErrorSonda);
        }

    }
}
