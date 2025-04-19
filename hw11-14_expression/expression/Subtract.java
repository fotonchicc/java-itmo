package expression;

public class Subtract extends BinaryOperations {
    public Subtract(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "-");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        return firstTerm - secondTerm;
    }
}
