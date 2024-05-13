package AxiomPackage;

public class Axiom {
    private int speed = 0;
    private String direction = "North";
    private boolean sonda = false;

    public Axiom() {
    }

    public Axiom Comand(char comand) {
        if (sonda && speed <= 1 && comand == 's') {
            throw new RuntimeException("Error Catasttrofico de la Sonda");
        } else {
            if (comand == 'i') {
                speed = speed + 1;
            }
            if (comand == 's') {
                speed = speed - 1;
                if (speed < 0) {
                    speed = 0;
                }
            }
        }
        if (sonda && comand == 'r' ||sonda && comand =='l'){
            throw new RuntimeException("Error Catasttrofico de la Sonda");
        } else{
            if (comand == 'r') {
                if (direction == "North") {
                    direction = "East";
                    return this;
                }
                if (direction == "East") {
                    direction = "South";
                    return this;
                }
                if (direction == "South") {
                    direction = "West";
                    return this;
                }
                if (direction == "West") {
                    direction = "North";
                    return this;
                }


            }
            if (comand == 'l') {
                if (direction == "North") {
                    direction = "West";
                    return this;
                }
                if (direction == "West") {
                    direction = "South";
                    return this;
                }
                if (direction == "South") {
                    direction = "East";
                    return this;
                }
                if (direction == "East") {
                    direction = "North";
                    return this;
                }
            }
        }
        if (comand == 'd') {
            if (speed == 0) {
                throw new RuntimeException("Error Catasttrofico de la Sonda");
            }
            sonda = true;
        }
        if (comand == 'f') {
            sonda = false;
        }
        return this;
    }

    public int currentSpeed() {
        return speed;
    }

    public String currentDirection() {
        return direction;
    }

    public boolean currentSonda() {
        return sonda;
    }
}
