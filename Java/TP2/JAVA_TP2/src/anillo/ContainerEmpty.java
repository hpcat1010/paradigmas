package anillo;

public class ContainerEmpty extends Container {

    public Object current() {
        throw new IllegalStateException(Ring.ringEmptyErrorDescription);
    }

    public Container previousContainer() {
        throw new IllegalStateException(Ring.ringEmptyErrorDescription);
    }

    public Container nextContainer() {
        throw new IllegalStateException(Ring.ringEmptyErrorDescription);
    }

    public void setNext(Container container) {
    }


    public Container remove() {
        throw new IllegalStateException(Ring.ringEmptyErrorDescription);
    }


    public void setPrevious(Container container) {
    }


}
