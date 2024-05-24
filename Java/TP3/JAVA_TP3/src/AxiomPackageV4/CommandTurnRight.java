package AxiomPackageV4;

public class CommandTurnRight extends Command {
    @Override
    public char character() {
        return 'r';
    }

    @Override
    public void handle(Axiom axiom) {
        axiom.turnRight();
    }
}
