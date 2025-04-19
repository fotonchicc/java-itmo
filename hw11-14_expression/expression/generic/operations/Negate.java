package expression.generic.operations;

import expression.generic.modes.GenericOperations;

public class Negate<T> extends UnaryOperations<T> {

    public Negate(GenericExpressions<T> term, GenericOperations<T> operation) {
        super(term, operation);
    }

    @Override
    public T countRes(T x) {
        return operation.negate(x);
    }
}
