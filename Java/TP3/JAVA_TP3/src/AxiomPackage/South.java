package AxiomPackage;

public class South extends Direccions{
    @Override
    public Direccions turnRight() {
        return new West();
    }

    @Override
    public Direccions turnLeft() {
        return new East();
    }

    @Override
    public String getDirection() {
        return "South";
    }

    @Override
    public void canTurn(boolean sonda) {
        if (sonda){
            throw new RuntimeException(Axiom.ErrorSonda);
        }
    }
}
