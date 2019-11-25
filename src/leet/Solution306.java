package leet;

import java.util.Stack;

public class Solution306 {
    Stack<Long> stack = new Stack<>();
    boolean flag = false;
    public static void main(String[] args) {
        //String num = "1002003005008001300";
        String num = "121474836472147483648";
        System.out.println(new Solution306().isAdditiveNumber(num));
    }

    public  boolean isAdditiveNumber(String num) {
        if (num.length() < 3)return false;
        helper(num);
        return flag;
    }

    public void helper(String num){
        if (flag)return;
        if (num.length() == 0){
            if(stack.size() >= 3) flag = true;
            return;
        }
        for (int i = 0;i<num.length();i++) {
            long num1;
            try {
                String str = num.substring(0, i + 1);
                num1 = Long.parseLong(str);
                if (str.charAt(0)=='0' && num1 != 0){
                    return;
                }
            }
            catch (NumberFormatException e){
                return;
            }

            if (stack.size() < 2) {
                stack.push(num1);
                helper(num.substring(i + 1));
                if (!stack.isEmpty()) stack.pop();
            }
            else {
                long tmp2 = stack.pop();
                long tmp1 = stack.pop();
                stack.push(tmp1);
                stack.push(tmp2);
                if (tmp1 + tmp2 == num1) {
                    stack.push(num1);
                    helper(num.substring(i + 1));
                    if (!stack.isEmpty()) stack.pop();
                }
                else if (num1 > tmp1 + tmp2) {
                    return;
                }
            }
        }
    }

}
