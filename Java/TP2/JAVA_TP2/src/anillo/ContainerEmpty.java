package anillo;

public class ContainerEmpty extends Container{
    public ContainerEmpty(Object cargo) {
        super(cargo);
    }

    public Object current() {
            throw new IllegalStateException("Ring is empty");
        }
    public Container next() {
        throw new IllegalStateException("Ring is empty");
    }
}
