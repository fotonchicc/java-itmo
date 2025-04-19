import java.util.Scanner;

public class BadTreap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        final int EPS = 710;
        int startValue = -710 * 25_000;
        for (int i = 0; i < n; i++) {
            System.out.println(startValue + i * EPS);
        }
    }
}
