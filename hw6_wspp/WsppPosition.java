import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WsppPosition {
    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(args[0], "utf8");
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                ));
                try {
                    Map<String, IntList> wordCount = new LinkedHashMap<>();
                    int num = 1;
                    while (reader.hasNextLine()) {
                        Scanner line = new Scanner(reader.nextLine());
                        int inx = 1;
                        try {
                            String word = line.nextWord();
                            while (true) {
                                if (word.isEmpty()) {
                                    break;
                                }
                                if (!wordCount.containsKey(word)) {
                                    IntList values = new IntList();
                                    values.insert(num, inx++);
                                    wordCount.put(word, values);
                                } else {
                                    wordCount.get(word).insert(num, inx++);
                                }
                                word = line.nextWord();
                            }
                            num++;
                        } finally {
                            line.close();
                        }
                    }
                    StringBuilder output = new StringBuilder(200);
                    for (String key : wordCount.keySet()) {
                        IntList value = wordCount.get(key);
                        int[] array = value.getArr();
                        int[] indx = value.getInx();
                        int size = value.getSize();
                        output.append(key).append(" ").append(size).append(" ");
                        for (int i = 0; i < size; i++) {
                            output.append(array[i]).append(":").append(indx[i]);
                            if (i < value.getSize() - 1) {
                                output.append(" ");
                            }
                        }
                        output.append(System.lineSeparator());
                    }
                    writer.write(output.toString());
                } catch (IOException e) {
                    System.out.println("Error when outputting data from a file: " + e.getMessage());
                } finally {
                    writer.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found: " + e.getMessage());
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
    }
}

