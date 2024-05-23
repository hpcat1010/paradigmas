package AxiomPackageV3;

public class Axiom {
    public static final String ErrorSonda = "Error Catasttrofico de la Sonda";
    public VelocityPilot currentSpeed = new Speed0();
    public Directions currentDirection = new Directions();
    public SondaState sondaState = new SondaNotDeployed();

    public Axiom() {
    }

    public void Message(String aMessage){
        aMessage.chars().mapToObj(c -> (char) c)
                .forEach(c -> {
                    Command.findCommand(c, this);
                });
    }

    public void forward() {
        currentSpeed = currentSpeed.speedUp();
    }

    public void Backward() {
        currentSpeed.canSlowDown();
        currentSpeed = currentSpeed.slowDown();
    }

    public void turnLeft() {
        sondaState.canTurn();
        currentDirection = currentDirection.turnLeft();
    }

    public void turnRight() {
        sondaState.canTurn();
        currentDirection = currentDirection.turnRight();
    }

    public void deploy() {
        currentSpeed.canDeploySonda();
        sondaState = new SondaDeployed();
    }

    public void retract() {
        sondaState = new SondaNotDeployed();
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
