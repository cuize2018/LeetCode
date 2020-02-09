package leet;

import java.util.*;

public class Solution316 {
    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(removeDuplicateLetters(s));
    }

    /**
     * 若栈中已经有当前元素，则直接去除当前元素
     * 若当前的栈顶元素比当前的元素字典序大，且当前元素的位置后面还有栈顶元素,
     * 将栈顶元素出栈, 将当前元素入栈, 这样来找到最优的排列
     * @param s
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if (stack.contains(c))continue;

            while (!stack.isEmpty() && stack.peek() > c && s.indexOf(stack.peek(), i) != -1){
                stack.pop();
            }
            stack.push(c);
        }

        StringBuilder str = new StringBuilder();
        for (Character character : stack) {
            str.append(character);
        }
        return str.toString();

    }

}
