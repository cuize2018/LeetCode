package 笔试.aiqiyi;

import java.util.Scanner;
import java.util.Stack;

public class No3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }
            else{
                if (stack.isEmpty()){
                    System.out.println("False");
                    return;
                }
                char temp = stack.peek();
                if (c == ')' && temp != '('){
                    System.out.println("False");
                    return;
                }
                else if (c == ']' && temp != '['){
                    System.out.println("False");
                    return;
                }
                else if (c == '}' && temp != '{'){
                    System.out.println("False");
                    return;
                }
                stack.pop();
            }
        }
        if (stack.isEmpty()) System.out.println("True");
        else {
            System.out.println("False");
        }
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        Stack<Character> stack = new Stack<>();
        int i = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }
            else if (c == '}'){
                if (stack.isEmpty())break;
                else {
                    char temp = stack.peek();
                    if (temp != '{')break;
                    else stack.pop();
                }
            }
            else if (c == ']'){
                if (stack.isEmpty())break;
                else {
                    char temp = stack.peek();
                    if (temp != '[')break;
                    else stack.pop();
                }
            }
            else if (c == ')'){
                if (stack.isEmpty())break;
                else {
                    char temp = stack.peek();
                    if (temp != '(')break;
                    else stack.pop();
                }
            }
        }
        if (i == s.length())System.out.println("True");
        else {
            System.out.println("False");
        }
    }

}
