package expression.generic.modes;

import expression.exceptions.DivisionByZero;
import expression.exceptions.OverflowException;

public class CheckedIntOperations implements GenericOperations<Integer> {

    @Override
    public Integer add(Integer a, Integer b) throws OverflowException {
        if ((a > 0 && b > 0 && a + b <= 0) ||
                (a < 0 && b < 0 && a + b >= 0)) {
            throw new OverflowException("overflow");
        }
        return a + b;
    }

    @Override
    public Integer divide(Integer a, Integer b) throws OverflowException, DivisionByZero {
        if (b == 0) {
            throw new DivisionByZero("division by zero");
        } else if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException("overflow");
        }
        return a / b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) throws OverflowException {
        if (a > 0 && b > 0 && a > Integer.MAX_VALUE / b ||
                a > 0 && b < 0 && b < Integer.MIN_VALUE / a ||
                a < 0 && b > 0 && a < Integer.MIN_VALUE / b ||
                a < 0 && b < 0 && a < Integer.MAX_VALUE / b) {
            throw new OverflowException("overflow");
        }
        return a * b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) throws OverflowException {
        if ((b > 0 && a < Integer.MIN_VALUE + b) ||
                (b < 0 && a > Integer.MAX_VALUE + b)) {
            throw new OverflowException("overflow");
        }
        return a - b;
    }

    @Override
    public Integer negate(Integer a) throws OverflowException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return -a;
    }

    @Override
    public Integer toMode(String a) {
        return Integer.parseInt(a);
    }
}
