package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Solution43 {
    public static void main(String[] args){
        String num1 = "999";
        String num2 = "99";
        System.out.println(multiply(num1, num2));
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))return "0";

        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 > len2){
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        StringBuilder num1_r = new StringBuilder(num1);
        num1_r.reverse();

        StringBuilder num2_r = new StringBuilder(num2);
        num2_r.reverse();

        List<StringBuilder> out = new ArrayList<>();
        for (int i = 0;i<num1_r.length();i++){
            int add = 0;
            StringBuilder one = new StringBuilder();
            for (int m = 0;m<i;m++){
                one.append("0");
            }

            for (int j = 0;j<num2_r.length();j++) {
                int a = Integer.parseInt(num1_r.substring(i,i+1));
                int b = Integer.parseInt(num2_r.substring(j,j+1));
                int val = a * b;

                if (val < 10) {
                    val += add;
                    add = 0;
                    if (val < 10) {
                        one.append(val);
                    }
                    else {
                        one.append(val%10);
                        add += val/10;
                    }
                } else {
                    int t = val % 10;
                    t += add;
                    add = 0;
                    add += val/10;
                    if (t < 10) {
                        one.append(t);
                    }
                    else {
                        one.append(t%10);
                        add += t/10;
                    }
                }
            }
            if (add != 0){one.append(add);}
            out.add(one);
        }

        StringBuilder c = new StringBuilder("0");
        for (int i = 0;i<out.size();i++){
            StringBuilder a =(out.get(i).reverse());
            c = Add(c, a);
        }
        return String.valueOf(c);
    }

    private static StringBuilder Add(StringBuilder a, StringBuilder b){
        if (a.length() < b.length()){
            a.reverse();
            int len = b.length()-a.length();
            for (int i = 0;i<len;i++){
                a.append("0");
            }
            a.reverse();
        }
        StringBuilder a1 = a.reverse();
        StringBuilder b1 = b.reverse();
        StringBuilder out = new StringBuilder();
        int add = 0;

        for (int i = 0;i<a.length();i++){
            int x = Integer.parseInt(a1.substring(i,i+1));
            int y = Integer.parseInt(b1.substring(i,i+1));
            int val = x+y;

            if (val > 10){
                val += add;
                add=0;
                out.append(val-10);
                add++;
            }else{
                val += add;
                add = 0;
                if (val >= 10){
                    out.append(val - 10);
                    add++;
                }else {
                    out.append(val);
                }
            }
        }
        if (add != 0)out.append(add);
        return out.reverse();
    }
}
