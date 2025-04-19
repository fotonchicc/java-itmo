package expression.exceptions;

import expression.EntireExpressions;

public class Main {
    public static void main(String[] args) {
        String expression = "(-1370135000 / yj) ◣ (y - -796849539)";
        ExpressionParser parser = new ExpressionParser();
        try {
            EntireExpressions exp = parser.parse(expression);
            System.out.println(exp.evaluate(-1728671668, 629589424, -489927566));
        } catch (OverflowException | DivisionByZero | NegativeValueException e) {
            System.err.println(e.getMessage());
        } catch (ParseException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
    }
}
