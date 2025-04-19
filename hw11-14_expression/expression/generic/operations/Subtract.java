package expression.generic.operations;

import expression.generic.modes.GenericOperations;

public class Subtract<T> extends BinaryOperations<T> {

    public Subtract(GenericExpressions<T> firstTerm, GenericExpressions<T> secondTerm, GenericOperations<T> operation) {
        super(firstTerm, secondTerm, operation);
    }

    @Override
    protected T countRes(T firstTerm, T secondTerm) {
        return operation.subtract(firstTerm, secondTerm);
    }
}
