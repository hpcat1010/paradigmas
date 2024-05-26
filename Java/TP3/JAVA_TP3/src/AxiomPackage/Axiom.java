package AxiomPackage;

public class Axiom {
    public static final String ErrorSonda = "Error catastrófico de la Sonda";
    public static VelocityControler currentSpeed;
    public DirectionsControler currentDirection;
    public static SondaControler sondaState;


    public Axiom() {
        sondaState = SondaControler.createInitialState();
        currentSpeed = VelocityControler.createInitialState();
        currentDirection = new DirectionsControler();
    }

    public void processMessage(String aMessage) {
        aMessage.chars().mapToObj(c -> (char) c)
                .forEach(c -> {
                    Command.findCommand(c, this);
                });
    }

    public void forward() {
        currentSpeed.speedUp();
    }

    public void backward() {
        currentSpeed.slowDown();
    }

    public void turnLeft() {
        currentDirection.turnLeft();
    }

    public void turnRight() {
        currentDirection.turnRight();
    }

    public void deploy() {
        sondaState.deploySonda();
    }

    public void retract() {
        sondaState.retractSonda();
    }

    public int currentSpeed() {
        return currentSpeed.getSpeed();
    }

    public String currentDirection() {
        return currentDirection.getDirection();
    }

    public boolean currentSonda() {
        return sondaState.whatAmI();
    }


}
