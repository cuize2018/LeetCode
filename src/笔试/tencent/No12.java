package 笔试.tencent;

import java.util.*;

public class No12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long k = scanner.nextLong();

        for (int i = 0; i < n; i++) {
            Long a = scanner.nextLong();
            if (i+1 == k){
                continue;
            }
            System.out.print(a + " ");
        }
    }
}
