package nowcoder.huawei;

import java.util.Scanner;

public class Solution02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();

        double res = getCubeRoot(a);
        String s = String.format("%.1f", res);
        System.out.println(Double.parseDouble(s));
    }

    public static double getCubeRoot(double input) {
        if (input == 1D) return 1D;

        double left = 0;
        double right = input;

        while ((right - left) > 0.001) {
            double mid = (left + right) / 2D;
            double t = mid * mid * mid;
            if (t == input) return mid;

            if (t < input) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
