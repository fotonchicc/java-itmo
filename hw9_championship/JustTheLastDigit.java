import java.io.IOException;
import java.util.Scanner;

public class JustTheLastDigit {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[][] paths = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                paths[i][j] = line.charAt(j) - '0';
            }
        }
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (paths[i][j] > 0) {
                    graph[i][j] = 1;
                    for (int k = j + 1; k < n; k++) {
                        int diff = paths[i][k] - paths[j][k];
                        paths[i][k] = (diff + 10) % 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }
        scanner.close();
    }
}