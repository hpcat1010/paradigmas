package anillo;

public class ContainerNotEmpty extends Container {
    private Container previous;
    private Container next;
    private Object current;


    public ContainerNotEmpty(Container previous, Container next, Object object) {
        this.next = next;
        current = object;
        this.previous = previous;
        previous.setNext(this);
        next.setPrevious(this);
    }

    public Container remove() {
        if (next==this) {
            return new ContainerEmpty();
        } else {
            previous.setNext(next);
            next.setPrevious(previous);
            return next;
        }
    }

    public void setPrevious(Container container) {
        previous = container;
    }

    public void setNext(Container container) {
        next = container;
    }

    public Object current() {
        return current;
    }

    public Container previousContainer() {
        return previous;
    }

    public Container nextContainer() {
        return next;
    }


//        if (contador == size()) {
//            for (int i = 0; i < contador; i++) {
//                next = next.previousContainer();
//            }
//        }
//        else {
//            next = container.nextContainer();
}
