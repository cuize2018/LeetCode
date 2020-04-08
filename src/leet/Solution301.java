package leet;

import java.util.*;

public class Solution301 {
    public static void main(String[] args) {
        String s = ")(";
        System.out.println(removeInvalidParentheses(s));
    }

    /**
     * 这道题要求你删除最小数量的无效括号，所以我们从删除一个括号开始枚举，删除一个括号，删除两个括号，删除三个括号...，
     * 每次删除的时候我们判断一下此时的字符是否合法（利用计数器或者栈），一旦合法，则不再需要继续删除，这样就能满足最小数量的要求。
     *
     * 想象一下，我们删除括号可以这样去做，每一次删除都是基于之前的删除，那么每一次删除只需要删除一个括号即可。
     * 我们可以清晰的感受到应该使用BFS, 每一层可能会出现重复的字符， 我们使用set集合去重
     *
     * @param s
     * @return
     */
    public static List<String> removeInvalidParentheses(String s) {
        //每一层我们去掉重复的元素
        Set<String> thisLevel = new HashSet<>();
        thisLevel.add(s);
        List<String> res = new ArrayList<>();

        while (!thisLevel.isEmpty()){
            for (String str : thisLevel) {
                if (isValid(str)){
                    res.add(str);
                }
            }
            if (!res.isEmpty())return res;

            Set<String> nextLevel = new HashSet<>();
            for (String str : thisLevel) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '(' || str.charAt(i)==')'){    // 因为字符中可能还有别的字符
                        nextLevel.add(str.substring(0,i) + str.substring(i+1));
                    }
                }
            }
            thisLevel = nextLevel;
            res.clear();
        }
        return res;
    }

    private static boolean isValid(String str) {
        int count = 0;
        for (char c : str.toCharArray()){
            if (c == '(')count++;
            else if (c == ')')count--;

            if (count < 0)return false;
        }
        return count == 0;
    }
}
