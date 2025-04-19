package expression.parser;

public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    public StringSource() {
        this.data = "";
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }

    public char lookNext() {
        char next = data.charAt(pos++);
        pos -= 1;
        return next;
    }

    @Override
    public int getPos() {
        return pos;
    }
}
