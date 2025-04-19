package expression;

public class Cbrt implements EntireExpressions {
    private final EntireExpressions term;

    public Cbrt(EntireExpressions term) {
        this.term = term;
    }

    @Override
    public int evaluate(int x) {
        return (int) Math.cbrt(term.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int) Math.cbrt(term.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "∛(" + this.term.toMiniString() + ")";
    }

}
