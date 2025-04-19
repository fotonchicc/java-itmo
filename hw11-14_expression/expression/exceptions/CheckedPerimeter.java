package expression.exceptions;

import expression.BinaryOperations;
import expression.EntireExpressions;

public class CheckedPerimeter extends BinaryOperations {

    public CheckedPerimeter(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "▯");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        if ((firstTerm < 0 || secondTerm < 0)) {
            throw new NegativeValueException("The side(s) for calculating perimeter is (are) negative");
        }
        if (firstTerm > Integer.MAX_VALUE / 2 - secondTerm) {
            throw new OverflowException("overflow");
        }

        return (firstTerm + secondTerm) * 2;
    }
}
