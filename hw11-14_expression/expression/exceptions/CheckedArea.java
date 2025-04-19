package expression.exceptions;

import expression.BinaryOperations;
import expression.EntireExpressions;

public class CheckedArea extends BinaryOperations {

    public CheckedArea(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "◣");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        if ((firstTerm < 0 || secondTerm < 0)) {
            throw new NegativeValueException("The side(s) for calculating area is (are) negative");
        }

        if (secondTerm == 0 || firstTerm == 0) {
            return 0;
        }

        if (firstTerm / 2 > Integer.MAX_VALUE / secondTerm) {
            throw new OverflowException("overflow");
        }

        if (firstTerm == 1) {
            return (int) (secondTerm / 2.0 * firstTerm);
        }
        return (int) (firstTerm / 2.0 * secondTerm);
    }
}
