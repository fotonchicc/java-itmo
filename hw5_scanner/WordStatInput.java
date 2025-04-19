import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class WordStatInput {
    public static void main(String[] args) {
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        try {
            Scanner reader = new Scanner(args[0], "utf8");
            try {
                StringBuilder sb = new StringBuilder();
                int read = reader.readScanner();
                while (read != -1) {
                    for (int i = 0; i < read; i++) {
                        char character = reader.nextChar(i);
                        if (Character.isLetter(character) || Character.getType(character) == Character.DASH_PUNCTUATION || character == '\'') {
                            sb.append(Character.toLowerCase(character));
                        } else {
                            if (!sb.isEmpty()) {
                                String word = sb.toString();
                                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                                sb.setLength(0);
                            }
                        }
                    }
                    read = reader.readScanner();
                }
                if (!sb.isEmpty()) {
                    String word = sb.toString();
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
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
                for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                    writer.write(entry.getKey() + " " + entry.getValue());
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



