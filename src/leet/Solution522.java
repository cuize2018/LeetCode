package leet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution522 {
    public static void main(String[] args) {
        String[] a = {"sdsdsa","adsade","dsavsad"};
        String[] b = {"aaa","aaa","aa"};

        System.out.println(findLUSlength(a));
    }

    /**
     * 如果存在这样的特殊序列，那么它一定是某个给定的字符串。
     *
     * 检查每个字符串是否是其他字符串的子序列。如果不是，则它是一个特殊序列。
     * 最后返回长度最大的特殊序列。如果不存在特殊序列，返回 -1。
     * @param strs
     * @return
     */
    public static int findLUSlength(String[] strs) {
        int max = -1;
        for (int i = 0;i<strs.length;i++){
            int j;
            for (j = 0;j<strs.length;j++){
                if (i != j){
                    if (isSubSeq(strs[i], strs[j])){
                        break;
                    }
                }
            }
            if (j == strs.length){
                max = Math.max(max, strs[i].length());
            }
        }

        return max;
    }

    /**
     * a是否为b的子序列
     * @param a
     * @param b
     * @return
     */
    public static boolean isSubSeq(String a, String b) {
        int j = 0;
        for (int i = 0;i<b.length() && j < a.length();i++){
            if (a.charAt(j) == b.charAt(i))j++;
        }
        return j == a.length();
    }
}
