package anillo;


public class Ring {
    static public String ringEmptyErrorDescription = "Ring is empty";
    private Container currentContainer = new ContainerEmpty();
    public Container lastAdded = new ContainerEmpty();
    private int contador = -1;
    public Ring() {

    }

    public Ring add(Object cargo) {
        contador++;
        currentContainer = new ContainerNotEmpty(lastAdded, cargo, contador);
        lastAdded = currentContainer;
        return this;
    }

    public Object current() {
        return currentContainer.current();
    }

    public Ring next() {
        currentContainer = currentContainer.nextContainer();
        return this;

    }

    public Ring remove() {
        currentContainer = currentContainer.remove();
        return this;
    }
}



