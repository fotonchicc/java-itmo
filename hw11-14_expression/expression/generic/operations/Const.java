package expression.generic.operations;

public class Const<T> implements GenericExpressions<T> {
    private final T constValue;

    public Const(T value) {
        this.constValue = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return constValue;
    }

}