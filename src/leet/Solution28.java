package leet;

import java.util.Arrays;

public class Solution28 {
    public static void main(String[] args) {
        String haystack = "hello";String needle = "ll";
//        System.out.println(strStr(haystack,needle));
        int idx = strStr2(haystack, needle);
        System.out.println(idx);
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0)return 0;

        for (int i = 0; i <= haystack.length()-needle.length(); i++) {
            String windowStr = haystack.substring(i, i + needle.length());
            if (windowStr.equals(needle)){
                return i;
            }
        }
        return -1;
    }

    public static int strStr2(String haystack, String needle) {
        if (needle.length() == 0)return 0;
        return kmpSearch(haystack, needle);
    }

    //KMP搜索算法
    private static int kmpSearch(String text, String pattern) {
        int[] prefixTable = getPrefixTable2(pattern);
        movePrefixTable(prefixTable);

        //text[i]       len(text)    = m
        //pattern[j]    len(pattern) = n

        int i = 0;int j = 0;
        int m = text.length();
        int n = pattern.length();

        char[] textArray = text.toCharArray();
        char[] patternArray = pattern.toCharArray();

        while (i < m){
            if (j == n-1 && textArray[i] == patternArray[j]){
                return i-j;
                //j = prefixTable[j];
            }
            if (textArray[i] == patternArray[j]){
                i++;j++;
            }
            else {
                j = prefixTable[j];
                if (j == -1){
                    i++;j++;
                }
            }
        }
        return -1;
    }

    //将前缀表整体右移一位
    private static void movePrefixTable(int[] prefixTable) {
        for (int i = prefixTable.length-1; i > 0; i--) {
            prefixTable[i] = prefixTable[i-1];
        }
        prefixTable[0] = -1;
    }

    //求解前缀表
    private static int[] getPrefixTable(String pattern) {
        char[] patternArray = pattern.toCharArray();
        int[] prefixTable = new int[pattern.length()];

        prefixTable[0] = 0;
        int i = 1;
        int len = 0;

        while (i < prefixTable.length){
            if (patternArray[i] == patternArray[len]){
                len++;
                prefixTable[i] = len;
                i++;
            }else {
                if (len > 0) {
                    len = prefixTable[len - 1];
                }
                else {
                    prefixTable[i] = 0;
                    i++;
                }
            }
        }
        return prefixTable;
    }

    private static int[] getPrefixTable2(String pattern) {
        int j = -1, i = 0;//i在前面, j在后面
        int[] b = new int[pattern.length()+1];
        b[i] = j;
        while(i < pattern.length())
        {
            while(j >= 0 && pattern.charAt(i) != pattern.charAt(j)) j = b[j];
            i++; j++;
            b[i] = j;
        }
        return b;
    }


}
