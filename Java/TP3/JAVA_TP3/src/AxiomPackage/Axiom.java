package AxiomPackage;

public class Axiom {
    public static final String ErrorSonda = "Error Catasttrofico de la Sonda";
    private Velocity currentSpeed = new Speed0();
    private Direccions currentDirection = new North();
    private boolean sonda = false;

    public Axiom() {
    }

    /*
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
        if (sonda && comand == 'r' || sonda && comand == 'l') {
            throw new RuntimeException("Error Catasttrofico de la Sonda");
        } else {
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
        */     // this but now make it work for strings such as "iirlrrllliiiddididrl"

    public Axiom Command(String command) {
        for (int i = 0; i < command.length(); i++) {
            if (sonda && currentSpeed.getSpeed() <= 1 && command.charAt(i) == 's') {
                throw new RuntimeException(ErrorSonda);
            } else {
                if (command.charAt(i) == 'i') {
                    currentSpeed =  currentSpeed.speedUp();
                }
                if (command.charAt(i) == 's') {
                    currentSpeed = currentSpeed.slowDown();
                }
            }
            if (sonda && command.charAt(i) == 'r' || sonda && command.charAt(i) == 'l') {
                throw new RuntimeException(ErrorSonda);
            } else {
                if (command.charAt(i) == 'r') {
                    currentDirection = currentDirection.turnRight();
                }
                if (command.charAt(i) == 'l') {
                    currentDirection = currentDirection.turnLeft();
                }
            }
            if (command.charAt(i) == 'd') {
                if (currentSpeed.getSpeed() == 0) {
                    throw new RuntimeException(ErrorSonda);
                }
                sonda = true;
            }
            if (command.charAt(i) == 'f') {
                sonda = false;
            }
        }
        return this;

    }

    public int currentSpeed() {
        return currentSpeed.getSpeed();
    }

    public String currentDirection() {
        return currentDirection.getDirection();
    }

    public boolean currentSonda() {
        return sonda;
    }
}
