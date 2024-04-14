package anillo;

public class ContainerNotEmpty extends Container{
    private Container previous;
    private Container next;
    private Object current;


    public ContainerNotEmpty(Container container, Object object,int contador) {
        next = this;
        current = object;
        previous = container;
        if (contador == size()) {
            for (int i = 0; i < contador; i++) {
                next = next.previousContainer();
            }
        }
        else {
            next = container.nextContainer();
        }
        previous.setNext(this);

    }
    public int size() {
        return previous.size() + 1;
    }

    public Container remove() {
       if (size() == 0) {
           return new ContainerEmpty();
       }
       else {
           previous.setNext(next);
           next.setNext(previous);
           return next;
       }
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




}
