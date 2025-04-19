package expression;

public class Minus implements EntireExpressions {
    private final EntireExpressions term;

    public Minus(EntireExpressions term) {
        this.term = term;
    }

    @Override
    public int evaluate(int x) {
        return -1 * term.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -1 * term.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "-(" + this.term.toString() + ")";
    }
}
