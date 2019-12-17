package leet;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class Solution402 {
    public static void main(String[] args) {
        String a = "9";
        int k = 1;
        System.out.println(removeKdigits(a,k));
    }

    public static String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        int n = num.length();
        int m = n-k;

        for (char c : num.toCharArray()){
            while (k > 0 && stack.size() > 0 && stack.peek() > c){
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while (stack.size() > m){
            stack.pop();
        }

        StringBuilder str = new StringBuilder();
        while (stack.size() > 0){
            str.append(stack.pop());
        }
        str.reverse();
        String out = "0";
        for (int i = 0;i<str.length();i++){
            if (str.charAt(i) != '0'){
                out = str.substring(i);
                break;
            }
        }
        return out;

    }

    /**
     * 维护一个递增栈，但当前元素小于栈顶元素，则移掉栈顶元素。
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits2(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = num.length();
        int m = n-k;

        for (char c : num.toCharArray()){
            while (k > 0 && stack.size() > 0 && stack.peekLast() > c){
                stack.removeLast();
                k--;
            }
            stack.add(c);
        }
        while (stack.size() > m){
            stack.removeLast();
        }


        while (stack.size() > 0){
            if (stack.peek() != '0'){
                break;
            }
            else {
                stack.remove();
            }
        }

        StringBuilder str = new StringBuilder();
        if (stack.size() > 0) {
            while (stack.size() > 0) str.append(stack.remove());
            return str.toString();
        }
        return "0";

    }
}
