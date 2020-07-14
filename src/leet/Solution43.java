package leet;

public class Solution43 {
    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(multiply2(num1, num2));
    }

    public static String multiply2(String num1, String num2) {
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') return "0";
        if (num1.equals("1")) return num2;
        if (num2.equals("1")) return num1;
        if (num1.length() < num2.length()) return multiply2(num2, num1);

        StringBuilder res = new StringBuilder("0");
        for (int i = num2.length() - 1; i >= 0; i--) {
            int add = 0;
            int v = num2.charAt(i) - '0';
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < (num2.length() - 1) - i; j++) {
                str.append('0');
            }

            for (int j = num1.length() - 1; j >= 0; j--) {
                int t = num1.charAt(j) - '0';
                int val = v * t + add;

                if (val >= 10) {
                    add = val / 10;
                    val = val % 10;
                } else add = 0;
                str.append(val);
            }
            if (add != 0) str.append(add);
            res = StringAdd(str, res);
        }
        return res.reverse().toString();
    }

    private static StringBuilder StringAdd(StringBuilder a, StringBuilder b) {
        if (a.charAt(a.length() - 1) == '0') return b;
        if (b.charAt(b.length() - 1) == '0') return a;
        if (a.length() < b.length()) return StringAdd(b, a);
        int add = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < b.length(); i++) {
            int x = a.charAt(i) - '0';
            int y = b.charAt(i) - '0';
            int val = x + y + add;

            if (val >= 10) {
                val = val - 10;
                add = 1;
            } else {
                add = 0;
            }
            res.append(val);
        }

        for (int i = b.length(); i < a.length(); i++) {
            int x = a.charAt(i) - '0';
            int val = x + add;

            if (val >= 10) {
                val = val - 10;
                add = 1;
            } else {
                add = 0;
            }
            res.append(val);
        }
        if (add != 0) res.append(add);
        return res;
    }
}
