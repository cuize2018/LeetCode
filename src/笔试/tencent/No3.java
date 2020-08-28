package 笔试.tencent;

import java.util.Scanner;

public class No3 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            long n = scanner.nextLong();
            long res = func3(n);
            System.out.println(res);
        }
    }

    private static long func(long n) {
        double t = n / 2D;
        if (t % 10 == 0){
            return sum((long)t-1) + sum((long)t+1);
        }

        long r = (long) Math.ceil(t);
        long l = (long) Math.floor(t);

        long suml = 0;
        long sumr = 0;

        while (l != 0){
            suml += l%10;
            l=l/10;
        }
        while (r != 0){
            sumr += r%10;
            r=r/10;
        }
        return suml+sumr;
    }

    private static long func3(long n) {
        String s = String.valueOf(n);
        long sum = (s.charAt(0) - '0')/2;
        for (int i = 1; i <= s.length()-1; i++) {
            sum = sum*10 + 9;
        }
        long r = n - sum;
        return sum(sum) + sum(r);
    }

    private static long sum(long l) {
        long sum =  0;
        while (l != 0){
            sum += l%10;
            l=l/10;
        }
        return sum;
    }
}
