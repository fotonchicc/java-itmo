package expression;

import java.util.Objects;

public class Variable implements EntireExpressions {
    private final String name;

    public Variable(String letter) {
        this.name = letter;
    }

    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        Character var = this.name.charAt(name.length() - 1);
        if (var.equals('x')) {
            return x;
        } else if (var.equals('y')) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Variable variable = (Variable) obj;
        return this.getName().equals(variable.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    public String getName() {
        return name;
    }
}
