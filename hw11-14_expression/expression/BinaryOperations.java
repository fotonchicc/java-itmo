package expression;

import java.util.Objects;

public abstract class BinaryOperations implements EntireExpressions {
    private final EntireExpressions firstTerm;
    private final EntireExpressions secondTerm;
    private final String sign;

    public BinaryOperations(EntireExpressions firstTerm, EntireExpressions secondTerm, String sign) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        this.sign = sign;
    }

    @Override
    public int evaluate(int x) {
        int res;
        res = countRes(firstTerm.evaluate(x), secondTerm.evaluate(x));
        return res;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int res;
        res = countRes(firstTerm.evaluate(x, y, z), secondTerm.evaluate(x, y, z));
        return res;
    }

    @Override
    public String toString() {
        return "(" + firstTerm + " " + sign + " " + secondTerm + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        BinaryOperations that = (BinaryOperations) obj;
        return Objects.equals(this.getFirstTerm(), that.getFirstTerm()) && Objects.equals(this.getSecondTerm(),
                that.getSecondTerm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstTerm(), getSecondTerm(), getClass());
    }

    public EntireExpressions getFirstTerm() {
        return firstTerm;
    }

    public EntireExpressions getSecondTerm() {
        return secondTerm;
    }

    protected abstract int countRes(int firstTerm, int secondTerm);
}
