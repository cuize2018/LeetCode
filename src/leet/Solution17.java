package leet;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Solution17 {


    public static void main(String[] args){
        String digits = "238";
        System.out.println(letterCombinations(digits));
    }

    /**
     * 深度优先
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        HashMap<Integer, String> phone = new HashMap<Integer, String>();
        phone.put(2,"abc");
        phone.put(3,"def");
        phone.put(4,"ghi");
        phone.put(5,"jkl");
        phone.put(6,"mno");
        phone.put(7,"pqrs");
        phone.put(8,"tuv");
        phone.put(9,"wxyz");

        List<String> out = new ArrayList<>();
        if (digits.length() == 0)return out;
        backTrack(phone,out,"",digits);

        return out;
    }

    public static void backTrack(HashMap<Integer,String> phone, List<String> out, String conbination, String nextDigit){
        if (nextDigit.length() == 0){
            out.add(conbination);
        }
        else{
            String one_digit = nextDigit.substring(0,1);
            String letters = phone.get(Integer.valueOf(one_digit));

            for (int i = 0;i<letters.length();i++){
                String letter = letters.substring(i,i+1);
                backTrack(phone,out,conbination+letter, nextDigit.substring(1));
            }
        }
    }

}
