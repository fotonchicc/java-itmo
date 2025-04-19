import java.io.Reader;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Scanner {
    private static final int BUFFER_SIZE = 1024;
    private static final String separator = System.lineSeparator();
    private static final int separatorLength = separator.length();
    private final Reader reader;
    private final char[] buffer = new char[BUFFER_SIZE];
    private int end;
    private int currentIndex;
    private int intValue;
    private Boolean isStr = false;
    private String word;

    public Scanner(String str) throws IOException {
        this.reader = new StringReader(str);
        this.end = readScanner();
        this.currentIndex = 0;
    }

    public Scanner(String file, String encoding) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(file), encoding);
        this.end = 0;
        this.currentIndex = 0;
    }

    public Scanner(InputStream in) throws IOException {
        this.reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        this.end = readScanner();
        this.currentIndex = 0;
    }

    public void close() throws IOException {
        reader.close();
    }

    public int readScanner() throws IOException {
        end = reader.read(buffer);
        currentIndex = 0;
        return end;
    }

    public boolean hasNextLine() throws IOException {
        boolean hasLine = false;
        if (currentIndex >= end) {
            readScanner();
            if (end == -1) {
                return hasLine;
            }
        }
        hasLine = true;
        isStr = true;
        return hasLine;
    }

    private String checkIfEndLine() throws IOException {
        StringBuilder isSeparator = new StringBuilder();
        isSeparator.append(buffer[currentIndex]);
        int i = 1;
        currentIndex++;
        while ((i < separatorLength) && (currentIndex < end)) {
            if (buffer[currentIndex] == separator.charAt(i)) {
                isSeparator.append(buffer[currentIndex]);
                i++;
                currentIndex++;
            } else {
                currentIndex++;
                return isSeparator.toString();
            }
        }
        if (i < separatorLength && currentIndex == end) {
            readScanner();
            if (buffer[currentIndex] == separator.charAt(i)) {
                int remainingSeparator = separatorLength - i;
                int j = 0;
                while ((j < remainingSeparator) && ((currentIndex) < end)) {
                    if (buffer[currentIndex] == separator.charAt(i)) {
                        isSeparator.append(buffer[currentIndex]);
                        i++;
                        j++;
                        currentIndex++;
                    } else {
                        currentIndex++;
                        return isSeparator.toString();
                    }
                }
            } else if (buffer[currentIndex] != separator.charAt(i)) {
                currentIndex++;
                return isSeparator.toString();
            }
        }
        return isSeparator.toString();
    }

    public String nextLine() throws IOException {
        String checker = "";
        if (!isStr) {
            isStr = hasNextLine();
            if (!isStr) {
                return "";
            }
        }
        if (buffer[currentIndex] == separator.charAt(0)) {
            if (separatorLength > 1) {
                checker = checkIfEndLine();
                if (Objects.equals(checker, separator)) {
                    isStr = false;
                    return "";
                }
            } else {
                currentIndex++;
                isStr = false;
                return "";
            }
        }
        StringBuilder str = new StringBuilder();
        str.append(checker);
        while (true) {
            if (currentIndex >= end) {
                readScanner();
                if ((end == -1)) {
                    break;
                }
            }
            if (buffer[currentIndex] == separator.charAt(0)) {
                if (separatorLength > 1) {
                    checker = checkIfEndLine();
                    if (Objects.equals(checker, separator)) {
                        break;
                    }
                } else {
                    currentIndex++;
                    break;
                }
            }
            str.append(buffer[currentIndex]);
            currentIndex++;
        }
        isStr = false;
        return str.toString();
    }

    public String nextWord() throws IOException {
        StringBuilder wd = new StringBuilder();
        while (true) {
            if (currentIndex == end) {
                readScanner();
            }
            if (end == -1) {
                break;
            }
            char currentChar = buffer[currentIndex];
            if (Character.isWhitespace(currentChar)) {
                if (!wd.isEmpty()) {
                    break;
                }
                currentIndex++;
                continue;
            }
            if (Character.isLetter(currentChar) || Character.getType(currentChar) == Character.DASH_PUNCTUATION || currentChar == '\'') {
                wd.append(currentChar);
                currentIndex++;
            } else {
                if (!wd.isEmpty()) {
                    currentIndex++;
                    break;
                } else {
                    currentIndex++;
                }
            }
        }
        return this.word = wd.toString().toLowerCase();
    }

        public boolean hasNextInt() throws IOException {
        StringBuilder num = new StringBuilder();
        while (true) {
            if (currentIndex == end) {
                readScanner();
            }
            if (end == -1) {
                break;
            }
            char currentChar = buffer[currentIndex];
            if (Character.isWhitespace(currentChar)) {
                if (!num.isEmpty()) {
                    break;
                }
                currentIndex++;
                continue;
            }
            if (Character.isDigit(currentChar) || (currentChar == '-')) {
                num.append(currentChar);
                currentIndex++;
            }
        }
        if (num.isEmpty()) {
            return false;
        } else {
            this.intValue = Integer.parseInt(num.toString());
            return true;
        }
    }

    public int nextInt() {
        return this.intValue;
    }

    public boolean hasNextNumber() throws IOException {
        StringBuilder wd = new StringBuilder();
        while (true) {
            if (currentIndex == end) {
                readScanner();
            }
            if (end == -1) {
                break;
            }
            char currentChar = buffer[currentIndex];
            if (Character.isWhitespace(currentChar)) {
                if (!wd.isEmpty()) {
                    break;
                }
                currentIndex++;
                continue;
            }
            wd.append(currentChar);
            currentIndex++;
        }
        if (wd.isEmpty()) {
            return false;
        } else {
            this.word = wd.toString();
            return true;
        }
    }

    public String nextNumber() {
        return this.word;
    }

    public char nextChar(int i) {
        return buffer[i];
    }
}


