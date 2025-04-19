package md2html;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    private static final String separator = System.lineSeparator();
    private static final int separatorLength = separator.length();

    private static void collectParagraph(StringBuilder paragraph, StringBuilder htmlContent) throws IOException {
        paragraph.setLength(paragraph.length() - separatorLength);
        ParagraphConverter paragraphMarkdown = new ParagraphConverter(paragraph.toString());
        String paraToHtml = paragraphMarkdown.convertToHtml();
        htmlContent.append(paraToHtml).append(System.lineSeparator());
        paragraph.setLength(0);
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
            ), 8192);
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                ), 8192);
                try {
                    StringBuilder paragraph = new StringBuilder();
                    StringBuilder htmlContent = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.isEmpty()) {
                            if (!paragraph.isEmpty()) {
                                collectParagraph(paragraph, htmlContent);
                            }
                        } else {
                            paragraph.append(line);
                            paragraph.append(separator);
                        }
                    }
                    if (!paragraph.isEmpty()) {
                        collectParagraph(paragraph, htmlContent);
                    }
                    writer.write(htmlContent.toString());
                } finally {
                writer.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Output file not found: " + e.getMessage());
            } finally {
            reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Contains characters that cannot be read: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error when in/outputting data from a file: " + e.getMessage());
        }
    }

}
