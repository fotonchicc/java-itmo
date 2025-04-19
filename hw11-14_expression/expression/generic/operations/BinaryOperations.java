package expression.generic.operations;

import expression.generic.modes.GenericOperations;

public abstract class BinaryOperations<T> implements GenericExpressions<T> {
    private final GenericExpressions<T> firstTerm;
    private final GenericExpressions<T> secondTerm;
    protected final GenericOperations<T> operation;

    public BinaryOperations(GenericExpressions<T> firstTerm, GenericExpressions<T> secondTerm, GenericOperations<T> operation) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        this.operation = operation;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        T res;
        res = countRes(firstTerm.evaluate(x, y, z), secondTerm.evaluate(x, y, z));
        return res;
    }

    protected abstract T countRes(T firstTerm, T secondTerm);
}
