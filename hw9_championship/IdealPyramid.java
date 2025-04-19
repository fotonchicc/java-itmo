import java.util.Scanner;

public class IdealPyramid {
    public static void main(String[] args) {
        long xl = Long.MAX_VALUE;
        long xr = Long.MIN_VALUE;
        long yl = Long.MAX_VALUE;
        long yr = Long.MIN_VALUE;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int h = scanner.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        int pyramidH = (int) Math.ceil((double) Math.max(xr - xl, yr - yl) / 2);
        int pyramidX = (int) Math.ceil((double) ((xl + xr) / 2));
        int pyramidY = (int) Math.ceil((double) ((yl + yr) / 2));
        System.out.println(pyramidX + " " + pyramidY + " " + pyramidH);
    }
}
