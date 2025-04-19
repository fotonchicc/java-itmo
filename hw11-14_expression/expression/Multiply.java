package expression;

public class Multiply extends BinaryOperations {
    public Multiply(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "*");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        return firstTerm * secondTerm;
    }
}
