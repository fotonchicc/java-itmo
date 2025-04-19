package expression.generic.parser;

import expression.exceptions.*;
import expression.generic.modes.GenericOperations;
import expression.generic.operations.*;
import expression.parser.CharSource;
import expression.parser.StringSource;

import java.util.List;

public class ExpressionParser<T> extends BaseParser<T> {
    private String expression;

    public ExpressionParser(GenericOperations<T> mode) {
        super(mode);
    }

    public GenericExpressions<T> parse(String expression) throws ParseException {
        this.expression = expression;
        return parseExpression(new StringSource(expression));
    }

    public GenericExpressions<T> parseExpression(CharSource expression) throws ParseException {
        establishSource(expression);
        GenericExpressions<T> result;
        result = plusMinus();
        if (eof()) {
            return result;
        } else {
            throw new ParseException("Bracket mismatch detected");
        }
    }

    private GenericExpressions<T> plusMinus() throws ParseException {
        GenericExpressions<T> firstTerm = multDiv();
        while (!eof()) {
            skipWhitespace();
            if (take('+')) {
                GenericExpressions<T> secTerm = multDiv();
                firstTerm = new Add<>(firstTerm, secTerm, mode);
            } else if (take('-')) {
                GenericExpressions<T> secTerm = multDiv();
                firstTerm = new Subtract<>(firstTerm, secTerm, mode);
            } else {
                break;
            }
        }
        return firstTerm;
    }

    private GenericExpressions<T> multDiv() throws ParseException {
        GenericExpressions<T> firstTerm = factor();
        while (!eof()) {
            skipWhitespace();
            if (take('*')) {
                GenericExpressions<T> secTerm = factor();
                firstTerm = new Multiply<>(firstTerm, secTerm, mode);
            } else if (take('/')) {
                GenericExpressions<T> secTerm = factor();
                firstTerm = new Divide<>(firstTerm, secTerm, mode);
            } else {
                break;
            }
        }
        return firstTerm;
    }

    private GenericExpressions<T> factor() throws ParseException {
        List<Character> variables = List.of('x', 'y', 'z');
        List<Character> brackets = List.of('(', '{', '[');
        skipWhitespace();
        GenericExpressions<T> res;
        char ch = currChar();
        if (brackets.contains(ch)) {
            return parseBrackets(ch);
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
            return new Variable<>(sb.toString());
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

    private GenericExpressions<T> parseUnaryMinus() throws ParseException {
        if (between('1', '9')) {
            return (parseNumber(-1));
        } else {
            return new Negate<>(factor(), mode);
        }
    }

    private GenericExpressions<T> parseNumber(int sign) {
        final StringBuilder sb = new StringBuilder();
        if (sign == -1) {
            sb.append("-");
        }
        takeDigits(sb);
        T num;
        try {
            num = mode.toMode(sb.toString());
        } catch (NumberFormatException e) {
            throw new OverflowException("overflow");
        }
        return new Const<>(num);
    }

    private void takeDigits(final StringBuilder sb) {
        while (between('0', '9')) {
            sb.append(currChar());
            take();
        }
    }

    private GenericExpressions<T> parseBrackets(char bracket) throws ParseException {
        take();
        char closeBracket = switch (bracket) {
            case '(' -> ')';
            case '{' -> '}';
            case '[' -> ']';
            default -> 0;
        };
        GenericExpressions<T> res = plusMinus();
        if (!take(closeBracket)) {
            throw new ParseException("Expected \"" + closeBracket + "\", found \"" + currChar() + "\"");
        }
        return res;
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
