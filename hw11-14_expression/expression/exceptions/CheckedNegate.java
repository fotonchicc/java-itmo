package expression.exceptions;

import expression.EntireExpressions;

public class CheckedNegate implements EntireExpressions {
    private final EntireExpressions term;

    public CheckedNegate(EntireExpressions term) {
        this.term = term;
    }

    @Override
    public int evaluate(int x) {
        return countRes(term.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return countRes(term.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "-(" + this.term.toString() + ")";
    }

    protected int countRes(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return -1 * x;
    }
}
