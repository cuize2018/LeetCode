package leet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solution150 {
    public static void main(String[] args){
        String[] a = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(a));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String str : tokens){
            if (str.contains("-") ){
                if (str.length() > 1) {
                    stack.push(Integer.parseInt(str));
                }
                else {
                    int num_out = exec(stack,str);
                    stack.push(num_out);
                }
            }
            else {
                if (isNumeric(str)){
                    stack.push(Integer.parseInt(str));
                }
                else {
                    int num_out = exec(stack,str);
                    stack.push(num_out);
                }
            }
        }
        return stack.pop();
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static int exec(Stack<Integer> stack, String op){
        int num1 = stack.pop();
        int num2 = stack.pop();
        int num3 = 0;
        switch (op){
            case "+":
                num3 = num2+num1;
                break;
            case "-":
                num3 = num2-num1;
                break;
            case "*":
                num3 = num2*num1;
                break;
            case "/":
                num3 = num2/num1;
                break;
        }
        return num3;
    }

}
