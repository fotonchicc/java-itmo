package expression;

public class Main {
    public static void main(String[] args) {
        EntireExpressions expression = new Add(
                new Subtract(new Multiply(new Variable("x"), new Variable("x")),
                        new Multiply(new Const(2), new Variable("x"))), new Const(1));
        System.out.println(expression.evaluate(Integer.parseInt(args[0])));
    }
}
