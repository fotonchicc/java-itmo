package expression.exceptions;

import expression.BinaryOperations;
import expression.EntireExpressions;

public class CheckedAdd extends BinaryOperations {

    public CheckedAdd(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "+");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        if ((firstTerm > 0 && secondTerm > 0 && firstTerm + secondTerm <= 0) ||
                (firstTerm < 0 && secondTerm < 0 && firstTerm + secondTerm >= 0)) {
            throw new OverflowException("overflow");
        }
        return firstTerm + secondTerm;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
