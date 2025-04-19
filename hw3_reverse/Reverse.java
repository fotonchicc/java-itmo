import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        int len = 0;
        int[][] numbers = new int[50][];
        while (console.hasNextLine()) {
            Scanner scannerInt = new Scanner(console.nextLine());
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
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = numbers[i].length - 1; j >= 0; j--)
                System.out.print(numbers[i][j] + " ");
            System.out.println();
        }
    }
}
