import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.LinkedHashMap;

public class Wspp {
    public static void main(String[] args) {
        Map<String, IntList> wordCount = new LinkedHashMap<>();
        try {
            Scanner reader = new Scanner(args[0], "utf8");
            try {
                StringBuilder sb = new StringBuilder();
                int entry_number = 1;
                int read = reader.readScanner();
                while (read != -1) {
                    for (int i = 0; i < read; i++) {
                        char character = reader.nextChar(i);
                        if (Character.isLetter(character) || Character.getType(character) == Character.DASH_PUNCTUATION || character == '\'') {
                            sb.append(Character.toLowerCase(character));
                        } else {
                            if (!sb.isEmpty()) {
                                String word = sb.toString();
                                if (!wordCount.containsKey(word)) {
                                    IntList values = new IntList();
                                    values.insert(entry_number);
                                    wordCount.put(word, values);
                                    sb.setLength(0);
                                } else {
                                    wordCount.get(word).insert(entry_number);
                                    sb.setLength(0);
                                }
                                entry_number ++;
                            }
                        }
                    }
                    read = reader.readScanner();
                }
                if (!sb.isEmpty()) {
                    String word = sb.toString();
                    if (!wordCount.containsKey(word)) {
                        IntList values = new IntList();
                        values.insert(entry_number);
                        wordCount.put(word, values);
                    } else {
                        wordCount.get(word).insert(entry_number);
                    }
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Contains characters that cannot be read: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error when outputting data from a file: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8
            ));
            try {
                for (Map.Entry<String, IntList> entry : wordCount.entrySet()) {
                    String key = entry.getKey();
                    IntList value = entry.getValue();
                    int[] array = value.getArr();
                    int size = value.getSize();
                    writer.write(key + " " + size + " ");
                    for (int i = 0; i < size; i ++) {
                        writer.write(String.valueOf(array[i]));
                        if (i < value.getSize() - 1) {
                            writer.write(" ");
                        }
                    }
                    writer.newLine();
                }
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Contains characters that cannot be read: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error when entering data into a file: " + e.getMessage());
        }
    }
}

