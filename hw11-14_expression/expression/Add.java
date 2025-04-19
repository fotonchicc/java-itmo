package expression;

public class Add extends BinaryOperations {
    public Add(EntireExpressions firstTerm, EntireExpressions secondTerm) {
        super(firstTerm, secondTerm, "+");
    }

    @Override
    protected int countRes(int firstTerm, int secondTerm) {
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
