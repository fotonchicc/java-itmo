package expression.generic.operations;

import expression.generic.modes.GenericOperations;

public class Add<T> extends BinaryOperations<T> {

    public Add(GenericExpressions<T> firstTerm, GenericExpressions<T> secondTerm, GenericOperations<T> operations) {
        super(firstTerm, secondTerm, operations);
    }

    @Override
    protected T countRes(T firstTerm, T secondTerm) {
        return operation.add(firstTerm, secondTerm);
    }
}
