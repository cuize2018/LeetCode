package nowcoder.huawei;

import java.util.Scanner;

public class Solution03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        String res = reverse(str);
        System.out.println(res);
    }

    private static String reverse(String str) {
        int size = str.length();
        StringBuilder s = new StringBuilder(str);

        for (int i = 0; i < size/2; i++) {
            char t = s.charAt(i);
            s.setCharAt(i, s.charAt(size-1-i));
            s.setCharAt(size-1-i, t);
        }
        return s.toString();
    }
}
