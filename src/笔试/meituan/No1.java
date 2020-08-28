package 笔试.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class No1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();

        if (n <= 1000){
            System.out.println(0);
            return;
        }

        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();
        int cnt = 0;

        for (long i = 1001; i <= n; i++) {
            long temp = i*4;
            if (temp <= n && isEcoh(i, temp)){
                left.add(i);
                right.add(temp);
                cnt++;
            }
        }
        System.out.println(cnt);
        for (int i = 0; i < left.size(); i++) {
            System.out.println(left.get(i) + " " + right.get(i));
        }
    }

    private static boolean isEcoh(long num, long temp) {
        long res = 0;
        while (num != 0){
            res = res*10 + (num %10);
            num = num/10;
        }
        return res == temp;
    }
}
