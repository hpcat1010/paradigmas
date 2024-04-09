package anillo;

import java.util.ArrayList;

public class ContainerNotEmpty extends Container{
    public ArrayList<Object> elements;
    public int currentIndex;
    public ContainerNotEmpty(Object cargo) {
        super(cargo);
    }
    public Object current() {
        return elements.get(currentIndex);
    }
    public Container next() {
        currentIndex = (currentIndex + 1) % elements.size();
        return this;
    }
}
