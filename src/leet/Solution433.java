package leet;

import javafx.util.Pair;

import java.util.*;

public class Solution433 {
    public static void main(String[] args) {

    }

    public int minMutation(String start, String end, String[] bank) {
        List<String> wordList = new ArrayList<>(Arrays.asList(bank));
        if (!wordList.contains(end))return -1;

        //help(beginWord,endWord,new HashSet<>(wordList),1);
        return bfs2(start, end, wordList);
    }

    public int bfs2(String beginWord, String endWord, List<String> wordList){
        int wordLen = beginWord.length();
        HashMap<String, ArrayList<String>> dict = new HashMap<>();
        //构建临界表
        for (String word:wordList) {
            for (int i = 0; i < wordLen; i++) {
                String new_word = word.substring(0,i) + "*" + word.substring(i+1,wordLen);
                ArrayList<String> list = dict.getOrDefault(new_word, new ArrayList<String>());
                list.add(word);
                dict.put(new_word, list);
            }
        }

        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(beginWord,0));

        HashMap<String, Boolean> visted = new HashMap<>();
        visted.put(beginWord,true);

        while (!queue.isEmpty()){
            Pair<String,Integer> node = queue.remove();
            String word = node.getKey();
            int len = node.getValue();

            for (int i = 0;i<wordLen;i++){
                String new_word = word.substring(0,i) + "*" + word.substring(i+1,wordLen);

                for (String processedWord: dict.getOrDefault(new_word, new ArrayList<>())){
                    if (processedWord.equals(endWord)){
                        return len+1;
                    }
                    if (!visted.containsKey(processedWord)) {
                        visted.put(processedWord, true);
                        queue.add(new Pair<>(processedWord, len + 1));
                    }
                }
            }
        }
        return -1;
    }
}
