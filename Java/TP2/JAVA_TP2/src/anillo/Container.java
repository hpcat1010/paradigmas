package anillo;

public abstract class Container {

    public abstract Object current();

    public abstract Container previousContainer();

    public abstract Container nextContainer();

    public abstract void setNext(Container container);

    public abstract void setPrevious(Container container);

    public abstract Container remove();
}
