import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManagingDifficulties {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int l = 0; l < n; l++) {
                a[l] = scanner.nextInt();
            }
            int count = 0;
            Map<Integer, Integer> countTriples = new HashMap<>();
            for (int j = n - 1; j >= 0; j--) {
                for (int i = 0; i < j; i++) {
                    int target = 2 * a[j] - a[i];
                    count += countTriples.getOrDefault(target, 0);
                }
                countTriples.put(a[j], countTriples.getOrDefault(a[j], 0) + 1);
            }
            System.out.println(count);
        }
    scanner.close();
    }
}
