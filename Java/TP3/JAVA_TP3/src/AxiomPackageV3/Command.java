package AxiomPackageV3;

import java.util.List;

public abstract class Command {
    static List<Command> commands = List.of
            (new CommandForward(),
                    new CommandBackward(),
                    new CommandTurnRight(),
                    new CommandTurnLeft(),
                    new CommandDeploy(),
                    new CommandRetract());

    static public void findCommand(char command, Axiom axiom) {
        commands.stream()
                .filter(c -> c.canHandle(command))
                .findAny()
                .ifPresentOrElse(
                        c -> c.handle(axiom),
                        () -> { throw new RuntimeException("Invalid command"); }
                );
    }

    public abstract char character();

    public boolean canHandle(char command) {
        return character() == command;
    }

    public abstract void handle(Axiom axiom);
}
