package leet;

import java.util.*;

public class Solution126 {
    public static void main(String[] args) {

    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> out = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        Queue<List<String>> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        List<String> list = new ArrayList<>(Arrays.asList(beginWord));
        queue.add(list);
        visited.add(beginWord);


        boolean flag = false;
        while (!queue.isEmpty() && !flag) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                List<String> path = queue.remove();
                String lastWord = path.get(path.size() - 1);
                char[] chars = lastWord.toCharArray();

                for (int j = 0; j < chars.length; j++) {
                    char temp = chars[j];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[j] = ch;
                        if (temp == ch) continue;

                        String str = new String(chars);
                        if (dict.contains(str) && !visited.contains(str)) {
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(str);

                            if (str.equals(endWord)) {
                                flag = true;
                                out.add(newPath);
                            }

                            queue.add(newPath);
                            subVisited.add(str);
                        }
                    }
                    chars[j] = temp;
                }
            }
            visited.addAll(subVisited);
        }
        return out;
    }

    public static List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> out = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        // 累积每一层的结果队列
        Queue<List<String>> queue = new ArrayDeque<>();
        // 已经访问过的单词集合：只找最短路径，所以之前出现过的单词不用出现在下一层
        Set<String> visited = new HashSet<>();
        // 字典中不包含目标单词
        if (!dict.contains(endWord)) return out;

        List<String> list = new ArrayList<>(Arrays.asList(beginWord));
        queue.add(list);
        visited.add(beginWord);

        // 是否到达符合条件的层：如果该层添加的某一单词符合目标单词，则说明截止该层的所有解为最短路径，停止循环
        boolean flag = false;
        while (!queue.isEmpty() && !flag) {
            // 上一层的结果队列
            int size = queue.size();

            // 该层添加的所有元素：每层必须在所有结果都添加完新的单词之后，再将这些单词统一添加到已使用单词集合
            // 如果直接添加到 visited 中，会导致该层本次结果添加之后的相同添加行为失败
            // 如：该层遇到目标单词，有两条路径都可以遇到，但是先到达的将该单词添加进 visited 中，会导致第二条路径无法添加
            Set<String> subVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                List<String> path = queue.remove();
                // 获取该路径上一层的单词
                String lastWord = path.get(path.size() - 1);

                // 寻找该单词的所有下一个符合条件的单词
                List<String> neighbors = getNeighbors(lastWord, dict);
                for (String str : neighbors) {
                    if (dict.contains(str) && !visited.contains(str)) {
                        // 生成新的路径
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(str);
                        // 如果该单词是目标单词：将该路径添加到结果集中，查询截止到该层
                        if (str.equals(endWord)) {
                            flag = true;
                            out.add(newPath);
                        }
                        // 将该路径添加到该层队列中
                        queue.add(newPath);
                        // 将该单词添加到该层已访问的单词集合中
                        subVisited.add(str);
                    }
                }
            }
            // 将该层所有访问的单词添加到总的已访问集合中
            visited.addAll(subVisited);
        }
        return out;
    }

    private static ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char chs[] = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch)
                    continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }
        }
        return res;
    }


    public static List<List<String>> findLadders3(String beginWord, String endWord, List<String> wordList) {
        Queue<List<String>> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();

        List<String> list = new ArrayList<>();
        list.add(beginWord);
        queue.add(list);
        visited.add(beginWord);

        boolean flag = false;
        while (!queue.isEmpty() && !flag) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> lastPath = queue.remove();
                String lastWord = lastPath.get(lastPath.size() - 1);

                List<String> neighbors = getNeighbors2(lastWord, dict);
                for (String neighbor : neighbors) {
                    if (dict.contains(neighbor) && !visited.contains(neighbor)) {
                        List<String> newPath = new ArrayList<>(lastPath);
                        newPath.add(neighbor);
                        if (neighbor.equals(endWord)) {
                            flag = true;
                            res.add(newPath);
                        }
                        queue.add(newPath);
                        subVisited.add(neighbor);
                    }
                }
            }
            visited.addAll(subVisited);
        }
        return res;
    }

    public static List<String> getNeighbors2(String node, Set<String> dict){
        List<String> res = new ArrayList<>();
        if (dict.size() == 0)return res;

        char[] chars = node.toCharArray();
        for (char c = 'a';  c <= 'z'; c++) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == c)continue;

                char old = chars[i];
                chars[i] = c;
                String newWord = String.valueOf(chars);
                if (dict.contains(newWord)){
                    res.add(newWord);
                }
                chars[i] = old;
            }
        }
        return res;
    }
}
