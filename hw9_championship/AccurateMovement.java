import java.util.Scanner;

public class AccurateMovement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.close();
        int minNumMoves = (int) (2 * Math.ceil((n - b) / (double) (b - a)) + 1);
        System.out.println(minNumMoves);
    }
}
