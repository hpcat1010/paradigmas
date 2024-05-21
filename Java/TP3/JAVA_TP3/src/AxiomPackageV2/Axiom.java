package AxiomPackageV2;

public class Axiom {
    public static final String ErrorSonda = "Error Catasttrofico de la Sonda";
    private VelocityPilot currentSpeed = new Speed0();
    private Directions currentDirection = new Directions();
    public SondaState sondaState = new SondaNotDeployed();

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
             // this but now make it work for strings such as "iirlrrllliiiddididrl"

    //            if (sonda && command.charAt(i) == 'r' || sonda && command.charAt(i) == 'l') {
//                throw new RuntimeException(ErrorSonda);
//            } else { */ //amongus


    //no iterativo/imperativo,hay q delegar!!!!!
    public void Command(String command) {

        for (int i = 0; i < command.length(); i++) {

            if (command.charAt(i) == 'r') {
                sondaState.canTurn(currentDirection);
                currentDirection = currentDirection.turnRight();
            }
            if (command.charAt(i) == 'l') {
                sondaState.canTurn(currentDirection);
                currentDirection = currentDirection.turnLeft();
            }

            if (command.charAt(i) == 'i') {
                currentSpeed = currentSpeed.speedUp();
            }
            if (command.charAt(i) == 's') {
                currentSpeed.canSlowDown();
                currentSpeed = currentSpeed.slowDown();
            }

            if (command.charAt(i) == 'd') {
                currentSpeed.canDeploySonda();
                sondaState = new SondaDeployed();
            }
            if (command.charAt(i) == 'f') {
                sondaState = new SondaNotDeployed();
            }
        }
    }


    public int currentSpeed() {
        return currentSpeed.getSpeed();
    }

    public String currentDirection() {
        return currentDirection.getDirection();
    }

    public boolean currentSonda() {
        return sondaState.isDeployed();
    }


}