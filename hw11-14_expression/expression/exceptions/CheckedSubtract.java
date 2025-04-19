package expression.exceptions;

import expression.BinaryOperations;
import expression.EntireExpressions;

public class CheckedSubtract extends BinaryOperations {

    public CheckedSubtract(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "-");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        if ((secondTerm > 0 && firstTerm < Integer.MIN_VALUE + secondTerm) ||
                (secondTerm < 0 && firstTerm > Integer.MAX_VALUE + secondTerm)) {
            throw new OverflowException("overflow");
        }
        return firstTerm - secondTerm;
    }
}
