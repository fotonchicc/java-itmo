package expression.exceptions;

import expression.BinaryOperations;
import expression.EntireExpressions;

public class CheckedMultiply extends BinaryOperations {

    public CheckedMultiply(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "*");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        if (firstTerm > 0 && secondTerm > 0 && firstTerm > Integer.MAX_VALUE / secondTerm ||
                firstTerm > 0 && secondTerm < 0 && secondTerm < Integer.MIN_VALUE / firstTerm ||
                firstTerm < 0 && secondTerm > 0 && firstTerm < Integer.MIN_VALUE / secondTerm ||
                firstTerm < 0 && secondTerm < 0 && firstTerm < Integer.MAX_VALUE / secondTerm) {
            throw new OverflowException("overflow");
        }
        return firstTerm * secondTerm;
    }
}
