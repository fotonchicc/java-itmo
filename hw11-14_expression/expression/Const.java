package expression;

public class Const implements EntireExpressions {
    private final int constValue;

    public Const(int value) {
        this.constValue = value;
    }

    public int evaluate(int x) {
        return constValue;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return constValue;
    }

    @Override
    public String toString() {
        return String.valueOf(constValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Const that = (Const) obj;
        return this.getConstValue() == that.getConstValue();
    }

    @Override
    public int hashCode() {
        return this.getConstValue();
    }

    public int getConstValue() {
        return constValue;
    }
}