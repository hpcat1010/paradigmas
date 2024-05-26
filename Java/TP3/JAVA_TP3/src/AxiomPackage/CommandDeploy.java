package AxiomPackage;

public class CommandDeploy extends Command {
    @Override
    public char character() {
        return 'd';
    }

    @Override
    public void handle(Axiom axiom) {
        axiom.deploy();
    }
}
