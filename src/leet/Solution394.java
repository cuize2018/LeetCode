package leet;

import java.util.Stack;

public class Solution394 {
    public static void main(String[] args) {
        String a = "2[abc]3[cd]ef";
        System.out.println(decodeString(a));
    }

    public static String decodeString(String s) {
        StringBuilder str = new StringBuilder();
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == ']') {
                StringBuilder val = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '[') {
                    val.append(stack.pop());
                }
                stack.pop();
                StringBuilder nums = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    nums.append(stack.pop());
                }
                int n = Integer.parseInt(nums.reverse().toString());
                String temp = val.reverse().toString();
                for (int j = 0; j < n; j++) {
                    for (char c : temp.toCharArray()) {
                        stack.push(c);
                    }
                }
            } else {
                stack.push(chars[i]);
            }
            i++;
        }
        while (!stack.isEmpty()){
            str.append(stack.pop());
        }
        return str.reverse().toString();
    }
}
