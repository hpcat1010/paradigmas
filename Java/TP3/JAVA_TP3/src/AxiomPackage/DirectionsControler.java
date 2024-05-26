package AxiomPackage;

import java.util.ArrayList;
import java.util.List;

public class DirectionsControler {

    int currentDirection = 0;
    List<String> directions = new ArrayList<>(List.of("North", "East", "South", "West"));



    public void turnRight() {
        Axiom.sondaState.canMove();
        currentDirection = (currentDirection + 1) % directions.size();
    }

    public void turnLeft() {
        Axiom.sondaState.canMove();
        currentDirection = (currentDirection + directions.size() - 1) % directions.size();
    }

    public String getDirection() {
        return directions.get(currentDirection);
    }


}
