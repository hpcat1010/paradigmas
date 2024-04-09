package anillo;
import java.util.ArrayList;

public class Ring {
    public ArrayList<Object> elements;
    public int currentIndex;

    public Ring() {
        elements = new ArrayList<>();
        elements.add(new ContainerEmpty(null));
        currentIndex = 0;
    }

    public Ring add(Object cargo) {
        elements.add(new ContainerNotEmpty(cargo));
        currentIndex = elements.size() - 1;
        return this;
    }

    public Object current() {

        if (elements.isEmpty()) {
            throw new IllegalStateException("Ring is empty");
        }
        return elements.get(currentIndex);
    }

    public Ring next() {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Ring is empty");
        }
        currentIndex = (currentIndex + 1) % elements.size();
        return this;
    }

    public Ring remove() {
        elements.remove(currentIndex);
        if (currentIndex >= elements.size()) {
            currentIndex = 0;
        }
        return this;
    }
}



