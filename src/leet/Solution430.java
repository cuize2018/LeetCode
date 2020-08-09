package leet;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution430 {
    public static void main(String[] args) {

    }

    public static String parseTernary(String expression) {
        if (expression.length() == 0)return null;
        Deque<Character> stack = new LinkedList<>();
        Deque<Character> stack2 = new LinkedList<>();

        char[] chars = expression.toCharArray();
        for (char c : chars) {
            stack.offerLast(c);
        }

        while (!stack.isEmpty()){
            while (stack.getLast() != '?'){
                Character c = stack.pollLast();
                stack2.offerLast(c);
            }
            stack.pollLast();
            Character flag = stack.removeLast();

            Character a = stack2.pollLast();
            stack2.pollLast();
            Character b = stack2.pollLast();

            if (flag == 'T'){
                stack2.offerLast(a);
            }
            else {
                stack2.offerLast(b);
            }
        }
        return String.valueOf(stack2.peekLast());
    }
}
