package leet;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class Solution20 {
    public static void main(String[] args){
        String s = "([]){}";
        System.out.println(isValid2(s));
    }

    public static boolean isValid(String s) {
        if (s.isEmpty())return true;
        HashMap<Character, Integer> level = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        int currLevel = 10;
        int count = 0;

        level.put('(', 1);
        level.put(')', 1);

        level.put('[', 2);
        level.put(']', 2);

        level.put('{', 3);
        level.put('}', 3);

        for (char c : s.toCharArray()){
            if (level.get(c) != currLevel || (c == stack.peek() && (count+1)<s.length())){
                stack.push(c);
                currLevel = level.get(c);
            }
            else {
                char x = stack.pop();
                if (stack.size() > 0){
                    currLevel = level.get(stack.peek());
                }
                else {
                    currLevel = 10;
                }
                if (x == c || level.get(x) != level.get(c)){
                    return false;
                }
            }
            count++;
        }
        if (stack.isEmpty()) return true;
        else return false;
    }

    public static boolean isValid2(String s) {
        if (s.isEmpty())return true;
        HashMap<Character, Integer> level = new HashMap<>();
        HashMap<Character, Integer> level2 = new HashMap<>();
        Stack<Character> stack = new Stack<>();

        level.put('(', 1);
        level.put('[', 2);
        level.put('{', 3);

        level2.put(')',1);
        level2.put(']',2);
        level2.put('}',3);

        for (char c : s.toCharArray()){
            if (level.containsKey(c)){
                stack.push(c);
            }
            else {
                char x;
                if (!stack.isEmpty()){
                    x = stack.pop();
                }
                else {
                    return false;
                }
                if (level.get(x) != level2.get(c)){
                    return false;
                }
            }
        }
        if (stack.isEmpty()) return true;
        else return false;
    }
}
