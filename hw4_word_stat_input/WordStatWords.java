import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;

public class WordStatWords {
    public static void main(String[] args) {
        TreeMap<String, Integer> wordCount = new TreeMap<>(Comparator.reverseOrder());
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
            ));
            try {
                StringBuilder selectedWord = new StringBuilder();
                char[] buffer = new char[1024];
                int read;
                while ((read = reader.read(buffer)) != -1) {
                    for (int i = 0; i < read; i++) {
                        char character = buffer[i];
                        if (Character.isLetter(character) || Character.getType(character) == Character.DASH_PUNCTUATION || character == '\'') {
                            selectedWord.append(Character.toLowerCase(character));
                        } else {
                            if (!selectedWord.isEmpty()) {
                                String word = selectedWord.toString();
                                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                                selectedWord.setLength(0);
                            }
                        }
                    }
                }
                if (!selectedWord.isEmpty()) {
                    String word = selectedWord.toString();
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












