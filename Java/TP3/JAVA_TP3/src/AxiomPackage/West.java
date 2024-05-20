package AxiomPackage;

public class West extends Direccions {
    @Override
    public Direccions turnRight() {
        return new North();
    }

    @Override
    public Direccions turnLeft() {
        return new South();
    }

    @Override
    public String getDirection() {
        return "West";
    }

    @Override
    public void canTurn(boolean sonda) {
        if (sonda){
            throw new RuntimeException(Axiom.ErrorSonda);
        }
    }
}
