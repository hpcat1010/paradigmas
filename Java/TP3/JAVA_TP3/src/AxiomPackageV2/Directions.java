package AxiomPackageV2;

import java.util.ArrayList;
import java.util.List;

public class Directions {

    int currentDirection = 0;
    List<String> directions = new ArrayList<>(List.of("North", "East", "South", "West"));

    public Directions turnRight() {
        currentDirection = (currentDirection + 1) % directions.size();
        return this;
    }

    public Directions turnLeft() {
        currentDirection = (currentDirection + directions.size() - 1) % directions.size();
        return this;
    }

    public String getDirection() {
        return directions.get(currentDirection);
    }


}
