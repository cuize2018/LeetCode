package nowcoder.huawei;

import java.util.Scanner;

public class Solution01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int res = func(a, b);
        System.out.println(res);
    }

    private static int func(int a, int b) {
        return a * b / gcd(a, b);
    }

    //最大公约数
    //r = a%b; 若r = 0, 则结果为a;
    //否则，a = b, b = r;
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
