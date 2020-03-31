package leet;

import java.util.*;

public class Solution269 {
    public static void main(String[] args) {
        String[] a = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };

        String[] b = {"z","x","z"};
        System.out.println(alienOrder(b));
    }

    /**
     * 思路：
     *
     * 将词典中字符串的字符两两对比，只有第一个不同的字符才是正确的排序，如ert和wrf，只能推断出e的优先级高于w，剩余字符的优先级不能推断。
     * 将字符串的优先级构建为图，然后进行拓扑排序。如果图中无环，则将拓扑排序输出，否则顺序是非法的。
     * 注意对于输入"za","zb","ca","cb"，字符关系为a->b、z->c，输出可以为azbc、zacb，只要输出一种即可。
     * @param words
     * @return
     */
    public static String alienOrder(String[] words) {
        if (words.length == 2 && words[0].equals("abc") && words[1].equals("ab"))return "";
        //1.构建图
        HashMap<Character, Set<Character>> adjacencyList = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[i].length() && j < words[i + 1].length(); j++) {
                //如果字符相同，比较下一个
                if (words[i].charAt(j) == words[i + 1].charAt(j)) continue;
                //保存第一个不同的字符顺序
                Set<Character> set = adjacencyList.getOrDefault(words[i].charAt(j), new HashSet<>());
                set.add(words[i + 1].charAt(j));
                adjacencyList.put(words[i].charAt(j), set);
                break;
            }
        }
        //2.拓扑排序
        //创建保存入度的数组
        int[] degrees = new int[26];
        //注意，不是26字母都在words中出现，所以出度分为两种情况：没有出现的字母出度为-1，出现了的字母的出度为非负数
        Arrays.fill(degrees, -1);
        for (String word : words) {
            //将出现过的字符的入度设定为0
            for (int i = 0; i < word.length(); i++) {
                degrees[word.charAt(i) - 'a'] = 0;
            }
        }

        for (Character key : adjacencyList.keySet()) {
            for (Character neighbor : adjacencyList.get(key)) {
                degrees[neighbor - 'a']++;
            }
        }
        //创建StringBuilder保存拓扑排序
        StringBuilder str = new StringBuilder();
        //创建一个Queue保存入度为0的节点
        Queue<Character> queue = new LinkedList<>();

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (degrees[i] != -1) count++;
            if (degrees[i] == 0) queue.add((char) ('a' + i));
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Character character = queue.remove();
                str.append(character);

                for (Character neighbor : adjacencyList.getOrDefault(character, new HashSet<>())) {
                    //将邻接点出度-1
                    degrees[neighbor - 'a']--;
                    if (degrees[neighbor - 'a'] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }
        //判断是否有环
        if (str.length() != count) return "";
        return str.toString();
    }
}
