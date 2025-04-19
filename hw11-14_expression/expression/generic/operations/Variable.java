package expression.generic.operations;

public class Variable<T> implements GenericExpressions<T> {
    private final String name;

    public Variable(String letter) {
        this.name = letter;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        Character var = this.name.charAt(name.length() - 1);
        if (var.equals('x')) {
            return x;
        } else if (var.equals('y')) {
            return y;
        } else {
            return z;
        }
    }
}
