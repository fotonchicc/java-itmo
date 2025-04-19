package expression.exceptions;

import expression.BinaryOperations;
import expression.EntireExpressions;

public class CheckedDivide extends BinaryOperations {

    public CheckedDivide(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "/");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        if (secondTerm == 0) {
            throw new DivisionByZero("division by zero");
        } else if (firstTerm == Integer.MIN_VALUE && secondTerm == -1) {
            throw new OverflowException("overflow");
        }
        return firstTerm / secondTerm;
    }
}
