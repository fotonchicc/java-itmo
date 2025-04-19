package expression.generic.operations;

import expression.generic.modes.GenericOperations;

public abstract class UnaryOperations<T> implements GenericExpressions<T> {
    private final GenericExpressions<T> term;
    protected final GenericOperations<T> operation;

    public UnaryOperations(GenericExpressions<T> term, GenericOperations<T> operation) {
        this.term = term;
        this.operation = operation;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return countRes(term.evaluate(x, y, z));
    }

    protected abstract T countRes(T term);
}