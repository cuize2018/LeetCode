package leet;

import java.util.*;

public class Solution227 {
    public static void main(String[] args){
        String s = " 3 / 2";
        System.out.println(calculate2(s));
    }

    public static int calculate(String s) {
        s = s.replace(" ","");
        Stack<String> dataStack = new Stack<>();
        Stack<String> opStack = new Stack<>();

        int i = 0;
        while (i < s.length()){
            StringBuilder num = new StringBuilder();
            while (i < s.length() && Character.isDigit(s.charAt(i))){
                num.append(s.charAt(i));
                i++;
            }
            if (num.length() > 0) {
                dataStack.push(num.toString());
            }
            if (i >= s.length())break;

            if (s.charAt(i) == '+' || s.charAt(i) == '-'){
                opStack.push(String.valueOf(s.charAt(i)));
                i++;
            }
            else if (s.charAt(i) == '*' || s.charAt(i) == '/'){
                int num1 = Integer.parseInt(dataStack.pop());
                char op = s.charAt(i);

                StringBuilder num2_str = new StringBuilder();
                i++;
                while (i < s.length() && Character.isDigit(s.charAt(i))){
                    num2_str.append(s.charAt(i));
                    i++;
                }
                int num2 = Integer.parseInt(num2_str.toString());
                if (op == '*'){
                    int out = num1*num2;
                    dataStack.push(String.valueOf(out));
                }
                else{
                    int out = num1/num2;
                    dataStack.push(String.valueOf(out));
                }
            }
        }

        Queue<String> dataQueue = new ArrayDeque<>(dataStack);
        Queue<String> opQueue = new ArrayDeque<>(opStack);

        int sum =Integer.parseInt(dataQueue.remove());
        while (dataQueue.size() > 0 && opQueue.size() > 0){
            String op = opQueue.remove();
            int num2 = Integer.parseInt(dataQueue.remove());

            if (op.equals("+")){
                sum += num2;
            }
            else {
                sum -= num2;
            }
        }
        return sum;

    }

    /**
     * 设置一个运算符双端队列和一个数据双端队列，先把乘除法计算出来，在计算加减法
     * @param s
     * @return
     */
    public static int calculate2(String s) {
        //s = s.replace(" ","");
        Deque<String> dataStack = new ArrayDeque<>();
        Deque<String> opStack = new ArrayDeque<>();

        int i = 0;
        while (i < s.length()){
            StringBuilder num = new StringBuilder();
            while (i < s.length() && Character.isDigit(s.charAt(i))){
                num.append(s.charAt(i));
                i++;
            }
            if (num.length() > 0) {
                dataStack.add(num.toString());
            }
            if (i >= s.length())break;

            if (s.charAt(i) == '+' || s.charAt(i) == '-'){
                opStack.add(String.valueOf(s.charAt(i)));
                i++;
            }
            else if (s.charAt(i) == '*' || s.charAt(i) == '/'){
                int num1 = Integer.parseInt(dataStack.removeLast());
                char op = s.charAt(i);

                StringBuilder num2_str = new StringBuilder();
                i++;
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i)==' ')){
                    if (s.charAt(i) != ' ') {
                        num2_str.append(s.charAt(i));
                    }
                    i++;
                }
                int num2 = Integer.parseInt(num2_str.toString());
                if (op == '*'){
                    int out = num1*num2;
                    dataStack.add(String.valueOf(out));
                }
                else{
                    int out = num1/num2;
                    dataStack.add(String.valueOf(out));
                }
            }
            else{
                i++;
            }
        }

        int sum =Integer.parseInt(dataStack.removeFirst());
        while (dataStack.size() > 0 && opStack.size() > 0){
            String op = opStack.removeFirst();
            int num2 = Integer.parseInt(dataStack.removeFirst());

            if (op.equals("+")){
                sum += num2;
            }
            else {
                sum -= num2;
            }
        }
        return sum;

    }


}
