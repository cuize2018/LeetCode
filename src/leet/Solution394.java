package leet;

import java.util.Stack;

public class Solution394 {
    public static void main(String[] args) {
        String a = "3[a]2[bc]";
        System.out.println(decodeString(a));
    }

    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()){
            if (c == ']'){
                StringBuilder tmp = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '['){
                    tmp.append(stack.pop());
                }
                stack.pop();

                int times = 0;
                int k = 1;
                while (!stack.isEmpty() && stack.peek() != ']' && Character.isDigit(stack.peek())){
                    times += Character.getNumericValue(stack.pop())*k;
                    k *= 10;
                }

                for (int m = 0;m<times;m++){
                    for (int j = tmp.length()-1;j>=0;j--){
                        char t = tmp.charAt(j);
                        stack.push(t);
                    }
                }
            }
            else {
                stack.push(c);
            }
        }

        StringBuilder out = new StringBuilder();
        while (!stack.isEmpty()){
            out.append(stack.pop());
        }
        return out.reverse().toString();
    }
}
