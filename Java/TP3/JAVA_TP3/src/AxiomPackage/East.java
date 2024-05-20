package AxiomPackage;

public class East extends Direccions {

    @Override
    public Direccions turnRight() {
        return new South();
    }

    @Override
    public Direccions turnLeft() {
        return new North();
    }

    @Override
    public String getDirection() {
        return "East";
    }

    @Override
    public void canTurn(Axiom axiom) {
        if (axiom.currentSonda()) {
            throw new RuntimeException(Axiom.ErrorSonda);
        }
    }
}


