package AxiomPackage;

public class CommandTurnLeft extends Command {
    @Override
    public char character() {
        return 'l';
    }

    @Override
    public void handle(Axiom axiom) {
        axiom.turnLeft();
    }
}
