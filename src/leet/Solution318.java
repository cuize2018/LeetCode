package leet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution318 {
    public static void main(String[] args) {
        String[] a = {"a","ab","abc","d","cd","bcd","abcd"};
        System.out.println(maxProduct2(a));
    }

    public static int maxProduct(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length())return -1;
                else if (o1.length() < o2.length())return 1;
                else return 0;
            }
        });

        int max_val = 0;
        for (int i = 0;i<words.length;i++){
            //if (words[i].length() < max_val)break;
            Set<Character> set = new HashSet<>();
            for (char c : words[i].toCharArray()){
                set.add(c);
            }
            for (int j = i+1;j<words.length;j++){
                int len = words[i].length()*words[j].length();
                if (len < max_val)break;
                boolean noSlap = true;
                for (char c : words[j].toCharArray()){
                    if (set.contains(c)){
                        noSlap = false;
                        break;
                    }
                }
                if (noSlap)max_val = Math.max(len, max_val);
            }
        }
        return max_val;

    }

    /**
     * 用二进制的一位表示某一个字母是否出现过，0表示没出现，1表示出现。
     * "abcd"二进制表示00000000 00000000 00000000 00001111、
     * "bc"二进制表示00000000 00000000 00000000 00000110。
     * 当两个字符串没有相同的字母时，二进制数与的结果为0。
     *
     * @param words
     * @return
     */
    public static int maxProduct2(String[] words) {

        int[] words_new = new int[words.length];
        for (int i = 0;i<words_new.length;i++){
            for (int j = 0;j<words[i].length();j++){
                words_new[i] |= 1<<(words[i].charAt(j)-'a');
            }
        }

        int max_val = 0;
        for (int i = 0;i<words_new.length;i++){
            for (int j = i+1;j<words_new.length;j++){
                if ((words_new[i]&words_new[j]) == 0){
                    max_val = Math.max(max_val, words[i].length()*words[j].length());
                }
            }
        }
        return max_val;
    }
}
