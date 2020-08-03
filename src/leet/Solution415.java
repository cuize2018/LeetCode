package leet;

public class Solution415 {
    public static void main(String[] args) {

    }

    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();

        if (len1 < len2) return addStrings(num2, num1);
        StringBuilder stringBuilder = new StringBuilder();
        int add = 0;

        int i = len1 - 1;
        int j = len2 - 1;

        while (j >= 0) {
            int a = num1.charAt(i--) - '0';
            int b = num2.charAt(j--) - '0';
            int sum = a + b + add;
            stringBuilder.append(sum >= 10 ? sum - 10 : sum);
            add = sum >= 10 ? 1 : 0;
        }
        while (i >= 0) {
            int a = num1.charAt(i--) - '0';
            int sum = a + add;
            stringBuilder.append(sum >= 10 ? sum - 10 : sum);
            add = sum >= 10 ? 1 : 0;
        }

        if (add != 0) stringBuilder.append(add);
        return stringBuilder.reverse().toString();
    }
}
