package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class Solution22 {
    public static void main(String[] args){
        System.out.println(generateParenthesis(2));
    }

    /**
     * 深度优先遍历 + 剪枝
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> out = new ArrayList<>();
        if (n <= 0)return out;
        if (n == 1){
            out.add("()");
            return out;
        }

        String s = "(";
        int left = 1;

        List<String> tmp = F("(", s, left, n,0);
        List<String> tmp2 = F(")", s, left, n,0);

        out.addAll(tmp);
        out.addAll(tmp2);

        return out;
    }

    public static List<String> F(String par, String already, int left, int n, int Layer){
        List<String> temp_out = new ArrayList<>();

        int layer_num = Layer+1;
        if (layer_num == n*2){
            return temp_out;
        }

        int left_ = left;
        String temp = already;

        if (par.equals("(")){
            left_++;
            if (left_ > n){
                return temp_out;
            }
            temp += "(";
        }
        else{
            left_--;
            if (left_ < 0){
                return temp_out;
            }
            temp += ")";
        }

        List<String> tmp = F("(", temp, left_, n, layer_num);
        List<String> tmp2 = F(")", temp, left_, n, layer_num);

        if (temp.length() == n*2 && left_ == 0) {
            temp_out.add(temp);
        }
        temp_out.addAll(tmp);
        temp_out.addAll(tmp2);
        return temp_out;
    }
}
