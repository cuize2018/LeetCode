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
     *  ***********************************************************
     *
     * 1.如果stk栈顶的元素大于当前遍历到的元素，根据上述原则，则应该在条件允许的情况下回避这一情况，
     * 根据题目要求（去重）,只有在后续还有这个栈顶元素的情况下才能将这个栈顶元素去掉（减少一个逆序）。
     *
     * 2.如果后续没有这个栈顶元素，则只能将它保留在这儿，即使它大于它的下一个元素。
     * 这样一来，stk中保存的两两相邻元素要么是顺序的（前一个小于后一个）
     * 要么是逆序的（前一个大于后一个，虽有违背最小排列，但由于没有后续相同元素可以替换，只能这样将就）。
     *
     * 3.由2可知如果当前元素存在于stk中，则说明stk中的这个元素已经是顺序的了（^-^），
     * 所以应该在循环中先判断这一情况，直接跳过当前元素。
     *
     * 4.同时，当根据条件去掉一个栈顶元素时，新的栈顶元素可能也大于当前元素，所以应该继续判断新的栈顶元素和当前元素的关系，
     * 用一个while循环，直到出现一个不满足循环条件的栈顶元素（要么小于当前元素，要么大于当前元素但后续又无与之相同的元素来替代）。

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
