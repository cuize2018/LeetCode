package leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution842 {
    public List<Integer> stack = new ArrayList<>();

    public static void main(String[] args) {

    }

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> out = new ArrayList<>();

        if (S.length() < 3)return out;
        help(S, 0);

        return stack;

    }

    public boolean help(String S, int start){
        if (start == S.length() && stack.size() > 2){
            return true;
        }

        for (int i = start;i<S.length();i++){
            String str = S.substring(start, i+1);

            if (Long.parseLong(str) > Integer.MAX_VALUE)break;
            if (!str.equals("0") && str.startsWith("0"))break;

            int val = Integer.parseInt(str);
            if (isFob(val)){
                stack.add(val);
                if (help(S, i+1)){
                    return true;
                }
                stack.remove(stack.size()-1);
            }
        }
        return false;
    }

    public boolean isFob(int val){
        if (stack.size() < 2){
            return true;
        }

        int num2 = stack.get(stack.size()-1);
        int num1 = stack.get(stack.size()-2);
        return  (num1+num2 == val);
    }


}
