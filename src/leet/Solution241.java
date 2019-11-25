package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution241 {
    private List<Integer> out = new ArrayList<>();

    public static void main(String[] args){
        String a ="2*3-4*5";
        String b ="2-1-1";

        Solution241 s = new Solution241();
        System.out.println(s.diffWaysToCompute2(a));

    }

    public List<Integer> diffWaysToCompute2(String input) {
        return partition(input);
    }

    public List<Integer> partition(String input) {
        List<Integer> result = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            result.add(Integer.valueOf(input));
            return result;
        }
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                for(Integer left : partition(input.substring(0, i))) {
                    for (Integer right : partition(input.substring(i + 1))) {
                        if (input.charAt(i) == '+') {
                            result.add(left + right);
                        } else if (input.charAt(i) == '-') {
                            result.add(left - right);
                        } else if (input.charAt(i) == '*') {
                            result.add(left * right);
                        }
                    }
                }
            }
        }
        return result;
    }

    public  List<Integer> diffWaysToCompute(String input) {
        helper(input);
        return out;
    }

    public void helper(String input){
        if (!input.contains("+") && !input.contains("*")){
            if (input.contains("-") && input.charAt(0) == '-') {
                out.add(Integer.parseInt(input));
                return;
            }
            else if (!input.contains("-")){
                out.add(Integer.parseInt(input));
                return;
            }
        }

        for (int i = 0;i < input.length();i++){
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
                if (i == 0 || (!Character.isDigit(input.charAt(i-1)))){
                    continue;
                }
                int k1 = i-1;int num1 = 0;int n = 0;
                while (k1 >= 0 && Character.isDigit(input.charAt(k1))){
                    num1 += Character.getNumericValue(input.charAt(k1))*Math.pow(10,n);
                    n++;
                    k1--;
                }
                if (k1 >= 0) {
                    if ((input.charAt(k1) == '-' && k1 == 0) ||
                            ((input.charAt(k1) == '-' && !Character.isDigit(input.charAt(k1 - 1))))) {
                        num1 = -num1;
                        k1--;
                    }
                }

                int k2 = i+1;int num2 = 0;
                StringBuilder str = new StringBuilder();
                if (input.charAt(k2) == '-'){
                    str.append(input.charAt(k2));
                    k2++;
                }

                while (k2 < input.length() && Character.isDigit(input.charAt(k2))){
                    str.append(input.charAt(k2));
                    k2++;
                }
                num2 = Integer.parseInt(str.toString());
                int tmp = 0;
                switch (input.charAt(i)){
                    case '+':
                        tmp = num1+num2;break;
                    case '-':
                        tmp = num1-num2;break;
                    case '*':
                        tmp = num1*num2;break;
                }
                String new_input = input.substring(0,k1+1) + tmp + input.substring(k2);
                helper(new_input);
            }
        }
    }
}

