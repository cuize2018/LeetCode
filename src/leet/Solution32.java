package leet;

import java.util.*;

public class Solution32 {
    public static void main(String[] args){
        String s = ")(";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        if (s.length() <= 1)return 0;
        Stack<Integer> stack_idx = new Stack<>();
        stack_idx.push(0-1);

        int max_len = 0;
        for (int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if (c == '('){
                stack_idx.push(i);
            }
            else {
                stack_idx.pop();
                if (!stack_idx.isEmpty()) {
                    int len = i - stack_idx.peek();
                    if (len > max_len) max_len = len;
                }
                else {
                    stack_idx.push(i);
                }
            }
        }
        return max_len;
    }
}
