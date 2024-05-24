package AxiomPackageV4;

public class CommandBackward extends Command {
    @Override
    public char character() {
        return 's';
    }

    @Override
    public void handle(Axiom axiom) {
        axiom.Backward();
    }
}
