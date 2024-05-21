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






}




