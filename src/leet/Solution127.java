package leet;

import javafx.util.Pair;

import java.util.*;

public class Solution127 {
    int min = Integer.MAX_VALUE;
    public static void main(String[] args) {
//        String beginWord = "hit";
//        String endWord = "cog";
//        String[] a = {"hot","dot","dog","lot","log","cog"};
//        String beginWord = "hot";
//        String endWord = "dot";
//        String[] a = {"hot","dot","dog"};

        String beginWord = "leet";
        String endWord = "code";
        String[] a ={"lest","leet","lose","code","lode","robe","lost"};

        List<String> wordList = new ArrayList<>(Arrays.asList(a));

        Solution127 sol = new Solution127();
        System.out.println(sol.ladderLength(beginWord,endWord,wordList));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))return 0;

        //help(beginWord,endWord,new HashSet<>(wordList),1);
        return bfs2(beginWord, endWord, wordList);
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
        queue.add(new Pair<>(beginWord,1));

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
        return 0;
    }

    
    public int bfs(String beginWord, String endWord, List<String> wordList){
        List<String> set = new ArrayList<>();

        HashSet<String> temp = new HashSet<>();
        set.add(beginWord);

        int len = 1;

        for (int i = 0;i<set.size();i++){
            String begin = set.get(i);

            List<String> new_words = new ArrayList<>(wordList);
            new_words.remove(begin);

            int count = 0;
            for (String word : new_words) {
                int diff = cal_different_chars(begin, word);
                if (diff == 1) {
                    if (count == 0) len++;
                    count++;
                    if (word.equals(endWord)) {
                        return len;
                    }
                    temp.add(word);
                }
            }

            set.clear();
            set.addAll(temp);

            temp.clear();
        }

        return 0;
    }

    public int cal_different_chars(String word, String temp_word){
        int num = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != temp_word.charAt(i)) {
                num++;
            }
        }
        return num;
    }
}
