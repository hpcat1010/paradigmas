package anillo;


public class Ring {
    static public String ringEmptyErrorDescription = "Ring is empty";
    private Container currentContainer = new ContainerEmpty();

    private int contador = 0;
    public Ring() {

    }

    public Ring add(Object cargo) {

        if (contador == 0) {
            currentContainer = new ContainerNotEmpty(currentContainer, currentContainer, cargo);
            currentContainer.setNext(currentContainer);
            currentContainer.setPrevious(currentContainer);
            contador++;
        } else {
            currentContainer = new ContainerNotEmpty(currentContainer.previousContainer(), currentContainer, cargo);
        }

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



