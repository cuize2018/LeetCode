package 笔试.aiqiyi;

import java.util.Scanner;

public class No1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int cnt = 0;
        for (int i = 5; i <= n; i+=5) {
            int curr = i;
            while (curr % 5 == 0){
                cnt++;
                curr /=5;
            }
        }
        System.out.println(cnt);

    }
}
