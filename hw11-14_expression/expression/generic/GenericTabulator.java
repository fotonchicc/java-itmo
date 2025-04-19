package expression.generic;

import expression.exceptions.ParseException;
import expression.exceptions.OverflowException;
import expression.exceptions.DivisionByZero;
import expression.generic.exceptions.*;
import expression.generic.modes.*;
import expression.generic.operations.GenericExpressions;
import expression.generic.parser.ExpressionParser;

public class GenericTabulator implements Tabulator {

    private GenericOperations<?> getMode(String mode) {
        return switch (mode) {
            case "i" -> new CheckedIntOperations();
            case "d" -> new DoubleOperations();
            case "bi" -> new BigIntegerOperations();
            default -> null;
        };
    }

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws InvalidModeException {
        GenericOperations<?> genericMode = getMode(mode);
        if (genericMode == null) {
            throw new InvalidModeException("Wrong mode was transmitted");
        }
        return tabulate(genericMode, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] tabulate(GenericOperations<T> mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        GenericExpressions<T> exp;
        try {
            exp = new ExpressionParser<>(mode).parse(expression);
        } catch (ParseException e) {
            return res;
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        res[i - x1][j - y1][k - z1] = exp.evaluate(mode.toMode(Integer.toString(i)),
                                mode.toMode(Integer.toString(j)),
                                mode.toMode(Integer.toString(k)));
                    } catch (OverflowException | DivisionByZero e) {
                        res[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Object[][][] res = new Object[0][0][0];
        try {
            res = new GenericTabulator().tabulate(args[0], args[1], -2, 2, -2, 2, -2, 2);
        } catch (InvalidModeException e) {
            System.err.println("Error: " + e.getMessage());
        }
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++) {
                    System.out.print(res[i + 2][j + 2][k + 2]);
                }
            }
        }
    }
}
