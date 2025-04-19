package expression.parser;

import expression.*;

import java.util.List;

public class ExpressionParser extends BaseParser implements TripleParser {

    @Override
    public EntireExpressions parse(String expression) {
        return parse(new StringSource(expression));
    }

    public EntireExpressions parse(CharSource expression) {
        establishSource(expression);
        return plusMinus();
    }

    private EntireExpressions plusMinus() {
        EntireExpressions firstTerm = multiDiv();
        while (!eof()) {
            skipWhitespace();
            if (take('+')) {
                EntireExpressions secTerm = multiDiv();
                firstTerm = new Add(firstTerm, secTerm);
            } else if (take('-')) {
                EntireExpressions secTerm = multiDiv();
                firstTerm = new Subtract(firstTerm, secTerm);
            } else {
                break;
            }
        }
        return firstTerm;
    }

    private EntireExpressions multiDiv() {
        EntireExpressions firstTerm = factor();
        while (!eof()) {
            skipWhitespace();
            if (take('*')) {
                EntireExpressions secTerm = factor();
                firstTerm = new Multiply(firstTerm, secTerm);
            } else if (take('/')) {
                EntireExpressions secTerm = factor();
                firstTerm = new Divide(firstTerm, secTerm);
            } else {
                break;
            }
        }
        return firstTerm;
    }

    private EntireExpressions factor() {
        List<Character> variables = List.of('x', 'y', 'z');
        skipWhitespace();
        EntireExpressions res;
        if (take('(')) {
            res = plusMinus();
            expect(')');
            return res;
        } else if (isLetter()) {
            StringBuilder sb = new StringBuilder();
            while (isLetter()) {
                sb.append(currChar());
                take();
            }
            Character var = sb.toString().charAt(sb.length() - 1);
            if (!variables.contains(var)) {
                throw error("Unexpected value for variable: " + var);
            }
            return new Variable(sb.toString());
        } else if (isDigit()) {
            res = parseNumber(1);
            return res;
        } else if (take('-')) {
            res = parseUnaryMinus();
            return res;
        } else if (take('∛')) {
            res = parseCbrt();
            return res;
        } else {
            throw error("Unexpected character: " + currChar());
        }
    }

    private EntireExpressions parseUnaryMinus() {
        if (between('1', '9')) {
            return (parseNumber(-1));
        } else {
            return new Minus(factor());
        }
    }

    private EntireExpressions parseCbrt() {
        final StringBuilder sb = new StringBuilder();
        boolean flag = false;
        char ch = currChar();
        if (test('-')) {
            flag = true;
            ch = checkNext();
        }
        if ('0' <= ch && ch <= '9') {
            if (flag) {
                sb.append(currChar());
                take();
            }
            takeDigits(sb);
            try {
                int num = (int) Math.cbrt(Integer.parseInt(sb.toString()));
                return new Const(num);
            } catch (final NumberFormatException e) {
                throw error("Invalid number: " + sb);
            }
        } else {
            return new Cbrt(factor());
        }
    }

    private EntireExpressions parseNumber(int sign) {
        final StringBuilder sb = new StringBuilder();
        if (sign == -1) {
            sb.append("-");
        }
        takeDigits(sb);
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (final NumberFormatException e) {
            throw error("Invalid number " + sb);
        }
    }

    private void takeDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(currChar());
            take();
        }
    }

    private boolean isLetter() {
        return between('a', 'z') || between('A', 'Z');
    }

    private boolean isDigit() {
        return between('0', '9');
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(currChar())) {
            take();
        }
    }
}
