package expression.generic.modes;

public interface GenericOperations<T> {

    T add(T a, T b);

    T divide(T a, T b);

    T multiply(T a, T b);

    T subtract(T a, T b);

    T negate(T a);

    T toMode(String a);
}
