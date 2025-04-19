package expression.generic.modes;

public class DoubleOperations implements GenericOperations<Double> {

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double toMode(String a) {
        return Double.parseDouble(a);
    }
}
