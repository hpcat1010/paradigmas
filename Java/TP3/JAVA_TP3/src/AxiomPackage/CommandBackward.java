package AxiomPackage;

public class CommandBackward extends Command {
    @Override
    public char character() {
        return 's';
    }

    @Override
    public void handle(Axiom axiom) {
        axiom.backward();
    }
}
