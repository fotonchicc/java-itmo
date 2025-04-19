package expression.parser;

public class BaseParser {
    private static final char END = '\0';
    private CharSource source;
    private char ch = 0xffff;

    public BaseParser() {
        this.source = new StringSource("");
    }

    protected BaseParser(final CharSource source) {
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
