package AxiomPackage;

public class CommandForward extends Command {

    public char character() {
        return 'i';
    }


    public void handle(Axiom axiom) {
        axiom.forward();
    }
}
