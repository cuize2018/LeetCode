package leet;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.scenario.effect.Brightpass;

import java.util.*;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 */
public class Solution30 {
    public static void main(String[] args){
        String s = "a";
        String[] words = {};

        System.out.println(findSubstring2(s,words));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> out = new ArrayList<>();
        int left = 0;
        if(s.length() == 1 && words.length == 1){
            for (String word : words){
                if (word.equals(s)){
                    out.add(0);
                    return out;
                }
            }
        }

        for (int i = 0;i<words.length;i++){
            List<Integer> smallerPiece = onePart(words, s, i, left, 0, true);
            out.addAll(smallerPiece);
        }
        Set<Integer> outSet = new HashSet<>(out);
        out = new ArrayList<>(outSet);

        return out;
    }

    private static List<Integer> onePart(String[] words, String s, int numElement, int left, int satrt, boolean first){
        List<Integer> out_part = new ArrayList<>();

        String word = words[numElement];
        String[] newWords = new String[words.length-1];

        int j = 0;
        for (int i = 0;i<words.length;i++){
            if (i != numElement){
                newWords[j] = words[i];
                j++;
            }
        }

        int right;
        while (left < s.length()) {
            right = left+1;
            for (int i = right; i <= s.length(); i++) {
                if ((i-left > word.length()))break;
                if (s.substring(left, i).equals(word)) {
                    if (newWords.length == 0){
                        out_part.add(satrt);
                        break;
                    }
                    for (int m = 0; m < newWords.length; m++) {
                        List<Integer> smallerPiece = onePart(newWords, s, m, i, satrt, false);
                        out_part.addAll(smallerPiece);
                    }
                }
            }
            if (first) {
                left++;
                satrt=left;
            }
            else break;
        }
        return out_part;
    }

    /**
     * 滑动窗口
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> out = new ArrayList<>();
        HashMap<String, Integer> wordsMap = new HashMap<>();
        HashMap<String, Integer> wordsMap2 = new HashMap<>();
        if (s.length() == 0 || words.length == 0)return out;

        for (String word :words){
            if (!wordsMap.containsKey(word)) {
                wordsMap.put(word, 1);
            }
            else {
                wordsMap.put(word, wordsMap.get(word)+1);
            }
        }

        int left = 0;
        int lenWord = words[0].length();
        int lenSubStr = lenWord*words.length;

        while (left <= s.length()-lenSubStr){
            boolean right = true;
            String subStr = s.substring(left,left+lenSubStr);
            int left_sub = 0;

            while (left_sub <= lenSubStr-lenWord){
                String tmp_word = subStr.substring(left_sub, left_sub+lenWord);
                if (wordsMap.containsKey(tmp_word)){
                    if (!wordsMap2.containsKey(tmp_word)){
                        wordsMap2.put(tmp_word, 1);
                    }
                    else {
                        wordsMap2.put(tmp_word, wordsMap2.get(tmp_word)+1);
                    }

                    if (wordsMap.get(tmp_word) < wordsMap2.get(tmp_word)){
                        right = false;
                        break;
                    }
                }
                else {
                    right = false;
                    break;
                }
                left_sub+=lenWord;
            }
            if (right){
                out.add(left);
            }
            left++;
            wordsMap2.clear();
        }
        return out;
    }
}
