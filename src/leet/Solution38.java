package leet;

public class Solution38 {
    public static void main(String[] args) {
        int n = 15;
        System.out.println(countAndSay(n));
    }

    public static String countAndSay(int n) {
        if (n == 1) return "1";

        String last = "1";
        for (int i = 2; i <= n; i++) {
            int j = 1;
            char c = last.charAt(0);
            int count = 1;
            StringBuilder str = new StringBuilder();
            while (j < last.length()) {
                if (last.charAt(j) != c) {
                    str.append(count);
                    str.append(c);
                    count = 0;
                    c = last.charAt(j);
                } else {
                    j++;
                    count++;
                }
            }
            str.append(count);
            str.append(c);
            last = str.toString();
        }
        return last;
    }
}
