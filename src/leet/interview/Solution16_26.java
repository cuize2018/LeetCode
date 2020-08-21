package leet.interview;

import java.util.*;

public class Solution16_26 {
    public static void main(String[] args) {
        String s = "3+5 / 2";
        System.out.println(calculate2(s));
    }

    public static int calculate(String s) {
        s = s.replace(" ", "");
        int n = s.length();
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        List<Character> op = new ArrayList<>();

        int i = 0;
        while (i < n) {
            StringBuilder num = new StringBuilder();
            while (i < n && Character.isDigit(chars[i])) {
                num.append(chars[i]);
                i++;
            }
            if (num.length() > 0) stack.push(Integer.parseInt(num.toString()));
            if (i == n) break;

            if (chars[i] == '+' || chars[i] == '-') {
                op.add(chars[i]);
                i++;
            } else if (chars[i] == '*' || chars[i] == '/') {
                StringBuilder b = new StringBuilder();
                int k = i + 1;
                while (k < n && Character.isDigit(chars[k])) {
                    b.append(chars[k]);
                    k++;
                }
                int l = stack.pop();
                int r = Integer.parseInt(b.toString());
                if (chars[i] == '*') stack.push(l * r);
                if (chars[i] == '/') stack.push(l / r);
                i = k;
            }
        }
        Deque<Integer> nums = new LinkedList<>(stack);
        for (Character character : op) {
            int l = nums.remove();
            int r = nums.remove();
            char operation = character;
            if (operation == '+') nums.addFirst(l + r);
            if (operation == '-') nums.addFirst(l - r);
        }
        return nums.removeLast();
    }

    public static int calculate2(String s) {
        s = s.replace(" ", "");

        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == ' ') {
                i++;
                continue;
            }
            char c = chars[i];
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                i++;
                while (i < chars.length && chars[i] == ' ') i++;
            }
            int num = 0;
            while (i < chars.length && Character.isDigit(chars[i])) {
                num = num * 10 + chars[i] - '0';
                i++;
            }
            if (c == '-') {
                num = -num;
            } else if (c == '*') {
                num = stack.pop() * num;
            } else if (c == '/') {
                num = stack.pop() / num;
            }
            stack.push(num);
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
