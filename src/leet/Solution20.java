package leet;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class Solution20 {
    public static void main(String[] args) {
        String s = "([]){}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        if (s.length() == 0) return true;
        if (s.length() % 2 != 0) return false;

        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char t = stack.pop();
                if (t == '(' && c != ')') return false;
                if (t == '[' && c != ']') return false;
                if (t == '{' && c != '}') return false;
            }
            i++;
        }
        return stack.isEmpty();
    }

    public static boolean isValid2(String s) {
        int n = s.length();
        if (n == 0) return true;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (stack.isEmpty()) {
                if (c == ')' || c == ']' || c == '}') return false;
                stack.push(c);
            } else {
                if (c == ')' && stack.peek() == '(') stack.pop();
                else if (c == ']' && stack.peek() == '[') stack.pop();
                else if (c == '}' && stack.peek() == '{') stack.pop();
                else if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else return false;
            }
        }
        return stack.isEmpty();
    }

}
