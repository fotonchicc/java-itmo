package expression.exceptions;

import expression.Const;
import expression.EntireExpressions;
import expression.Variable;
import expression.parser.BaseParser;
import expression.parser.CharSource;
import expression.parser.StringSource;

import java.util.List;

public class ExpressionParser extends BaseParser implements TripleParser {
    private String expression;

    @Override
    public EntireExpressions parse(String expression) throws ParseException {
        this.expression = expression;
        return parseExpression(new StringSource(expression));
    }

    public EntireExpressions parseExpression(CharSource expression) throws ParseException {
        establishSource(expression);
        EntireExpressions result;
        result = areaPerimeter();
        if (eof()) {
            return result;
        } else {
            throw new ParseException("Bracket mismatch detected");
        }
    }

    private EntireExpressions areaPerimeter() throws ParseException {
        EntireExpressions firstTerm = plusMinus();
        while (!eof()) {
            skipWhitespace();
            if (take('◣')) {
                EntireExpressions secTerm = plusMinus();
                firstTerm = new CheckedArea(firstTerm, secTerm);
            } else if (take('▯')) {
                EntireExpressions secTerm = plusMinus();
                firstTerm = new CheckedPerimeter(firstTerm, secTerm);
            } else {
                break;
            }
        }
        return firstTerm;
    }

    private EntireExpressions plusMinus() throws ParseException {
        EntireExpressions firstTerm = multDiv();
        while (!eof()) {
            skipWhitespace();
            if (take('+')) {
                EntireExpressions secTerm = multDiv();
                firstTerm = new CheckedAdd(firstTerm, secTerm);
            } else if (take('-')) {
                EntireExpressions secTerm = multDiv();
                firstTerm = new CheckedSubtract(firstTerm, secTerm);
            } else {
                break;
            }
        }
        return firstTerm;
    }

    private EntireExpressions multDiv() throws ParseException {
        EntireExpressions firstTerm = factor();
        while (!eof()) {
            skipWhitespace();
            if (take('*')) {
                EntireExpressions secTerm = factor();
                firstTerm = new CheckedMultiply(firstTerm, secTerm);
            } else if (take('/')) {
                EntireExpressions secTerm = factor();
                firstTerm = new CheckedDivide(firstTerm, secTerm);
            } else {
                break;
            }
        }
        return firstTerm;
    }

    private EntireExpressions factor() throws ParseException {
        List<Character> variables = List.of('x', 'y', 'z');

        skipWhitespace();
        EntireExpressions res;
        if (take('(')) {
            res = areaPerimeter();
            if (!take(')')) {
                skipWhitespace();
                throw new ParseException("Expected \")\", found \"" + currChar() + "\"");
            }
            return res;
        } else if (isLetter()) {
            StringBuilder sb = new StringBuilder();
            while (isLetter()) {
                sb.append(currChar());
                take();
            }
            Character var = sb.toString().charAt(sb.length() - 1);
            if (!variables.contains(var)) {
                throw new ParseException("Unexpected value for variable: " + var + " >>> \"" +
                        expression.substring(0, getPosition() - 2) + "{HERE ->}" + var
                        + "{<- HERE}" + expression.substring(getPosition() - 1) + "\"");
            }
            return new Variable(sb.toString());
        } else if (isDigit()) {
            res = parseNumber(1);
            return res;
        } else if (take('-')) {
            res = parseUnaryMinus();
            return res;
        } else {
            throw new ParseException("Unexpected character: \"" + currChar() + "\" >>> \"" +
                    expression.substring(0, getPosition() - 1) + "{HERE ->}" + currChar()
                    + "{<- HERE}" + expression.substring(getPosition()) + "\"");
        }
    }

    private EntireExpressions parseUnaryMinus() throws ParseException {
        if (between('1', '9')) {
            return (parseNumber(-1));
        } else {
            return new CheckedNegate(factor());
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
        } catch (NumberFormatException e) {
            throw new OverflowException("overflow");
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
