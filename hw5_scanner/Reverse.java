import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        try {
            Scanner console = new Scanner(System.in);
            try {
                int len = 0;
                int[][] numbers = new int[50][];
                while (console.hasNextLine()) {
                    try {
                        Scanner scannerInt = new Scanner(console.nextLine());
                        try {
                            int[] numLine = new int[50];
                            int inx = 0;
                            while (scannerInt.hasNextInt()) {
                                if (inx == numLine.length - 1) {
                                    numLine = Arrays.copyOf(numLine, numLine.length * 2);
                                }
                                numLine[inx++] = scannerInt.nextInt();
                            }
                            numLine = Arrays.copyOf(numLine, inx);
                            if (len == numbers.length - 1) {
                                numbers = Arrays.copyOf(numbers, numbers.length * 2);
                            }
                            numbers[len++] = numLine;
                        } finally {
                            scannerInt.close();
                        }
                    } catch (IOException e) {
                        System.out.println("Error when outputting data from a file: " + e.getMessage());
                    }
                }
                for (int i = len - 1; i >= 0; i--) {
                    for (int j = numbers[i].length - 1; j >= 0; j--) {
                        System.out.print(numbers[i][j]);
                        if (j > 0) {
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }
            } finally {
                console.close();
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("Contains characters that cannot be read: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error when outputting data from a file: " + e.getMessage());
        }
    }
}
