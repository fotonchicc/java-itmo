package expression.generic.parser;

import expression.generic.modes.GenericOperations;
import expression.parser.CharSource;
import expression.parser.StringSource;

public class BaseParser<T> {
    private static final char END = '\0';
    private CharSource source;
    private char ch = 0xffff;
    protected final GenericOperations<T> mode;

    public BaseParser(GenericOperations<T> mode) {
        this.source = new StringSource("");
        this.mode = mode;
    }

    protected BaseParser(final CharSource source, GenericOperations<T> mode) {
        this.mode = mode;
        establishSource(source);
    }

    protected char take() {
        ch = source.hasNext() ? source.next() : END;
        return ch;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected boolean eof() {
        return test(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void establishSource(CharSource source) {
        this.source = source;
        take();
    }

    protected char currChar() {
        return ch;
    }

    protected char checkNext() {
        return source.lookNext();
    }

    protected int getPosition() {
        return source.getPos();
    }
}
