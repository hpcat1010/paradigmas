package AxiomPackage;

public class CommandRetract extends Command {
    @Override
    public char character() {
        return 'f';
    }

    @Override
    public void handle(Axiom axiom) {
        axiom.retract();
    }
}
