package leet;

import Learning.Kmp;

public class Solution459 {
    public static void main(String[] args) {

    }

    public static boolean repeatedSubstringPattern(String s) {
        if (s.length() == 0)return false;
        if (s.length() == 1)return false;

        for (int len = 1; len <= s.length() / 2; len++) {
            if (func(s.substring(len), s.substring(0, len))) {
                return true;
            }
        }
        return false;
    }

    private static boolean func(String s, String part) {
        int len = part.length();
        for (int i = 0; i < s.length(); i+=len) {
            if (i+len <= s.length()) {
                if (!s.substring(i, i + len).equals(part)) {
                    return false;
                }
            }
            else return false;
        }
        return true;
    }

    /**
     * 生成一个新字符串s+s -> ss
     * 如果 s 中没有循环节，那么 ss 中必然有且只有两个 s，此时从 ss[1] 处开始寻找 s ，必然只能找到第二个，所以此时返回值为 s.size()。
     * 当 s 中有循环节时，设循环节为 r，其长度为 l，那么 ss 中必然有 s.size()/l + 1 个 s。
     * 因为去掉了第一个 S 的第一个字符 (代码中，(s+s).find(s, 1)， 是从 ss[1] 处开始 find )
     * 所以此时必回找到第二个 s 的起点。
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern2(String s) {
        int n = s.length();
        if (n == 0)return false;
        String ss = (s+s);
        int index = ss.indexOf(s, 1);
        return index != n;
    }


    public static boolean repeatedSubstringPattern3(String s) {
        int n = s.length();
        if (n == 0)return false;
        String ss = (s+s);
        Kmp kmp = new Kmp(ss.substring(1), s);
        int index = kmp.kmpSearch();
        return index+1 != n;
    }
}
