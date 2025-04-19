package expression;

public class Divide extends BinaryOperations {
    public Divide(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "/");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
        return firstTerm / secondTerm;
    }
}
