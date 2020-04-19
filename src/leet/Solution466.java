package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution466 {
    public static void main(String[] args) {
        String s1 = "acb";
        int n1 = 4;
        String s2 = "ab";
        int n2 = 2;

        System.out.println(getMaxRepetitions(s1, n1, s2, n2));
    }

    //暴力
    public static int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int count1 = 0;
        int count2 = 0;

        int j = 0;
        while (count1 < n1) {
            for (char c : c1) {
                if (c == c2[j]) {
                    if (j == s2.length() - 1) {
                        j = 0;
                        count2++;
                    } else {
                        j++;
                    }
                }
            }
            count1++;
        }
        return count2 / n2;
    }

    /**
     * recall 是我们用来找循环节的变量，它是一个哈希映射
     * 我们如何找循环节？假设我们遍历了 s1cnt 个 s1，此时匹配到了第 s2cnt 个 s2 中的第 index 个字符
     * 如果我们之前遍历了 s1cnt' 个 s1 时，匹配到的是第 s2cnt' 个 s2 中同样的第 index 个字符，那么就有循环节了
     * 我们用 (s1cnt', s2cnt', index) 和 (s1cnt, s2cnt, index) 表示两次包含相同 index 的匹配结果
     * 那么哈希映射中的键就是 index，值就是 (s1cnt', s2cnt') 这个二元组
     * 循环节就是；
     * - 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
     * - 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
     * 那么还会剩下 (n1 - s1cnt') % (s1cnt - s1cnt') 个 s1, 我们对这些与 s2 进行暴力匹配
     * 注意 s2 要从第 index 个字符开始匹配
     *
     * @param s1
     * @param n1
     * @param s2
     * @param n2
     * @return
     */
    public static int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        if (n1 == 0) return 0;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int s1Count = 0;
        int s2Count = 0;
        int index = 0;
        Map<Integer, Pair> recall = new HashMap<>();
        Pair preLoop;
        Pair inLoop;

        while (true) {
            //我们多遍历一个 s1，看看能不能找到循环节
            for (char c : c1) {
                if (c == c2[index]) {
                    index++;
                    if (index == s2.length()) {
                        s2Count++;
                        index = 0;
                    }
                }
            }
            s1Count++;
            //还没有找到循环节，所有的 s1 就用完了
            if (s1Count == n1) return s2Count / n2;

            //出现了之前的 index，表示找到了循环节
            if (recall.containsKey(index)) {
                Pair pair = recall.get(index);
                int s1CountPrime = pair.getX();
                int s2CountPrime = pair.getY();
                // 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
                preLoop = new Pair(s1CountPrime, s2CountPrime);
                // 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
                inLoop = new Pair(s1Count - s1CountPrime, s2Count - s2CountPrime);
                break;
            } else recall.put(index, new Pair(s1Count, s2Count));
        }
        //ans 存储的是 S1 包含的 s2 的数量，考虑的之前的 pre_loop 和 in_loop
        int ans = preLoop.getY() + (n1 - preLoop.getX()) / inLoop.getX() * inLoop.getY();
        //S1 的末尾还剩下一些 s1，我们暴力进行匹配
        int rest = (n1 - preLoop.getX()) % inLoop.getX();
        for (int i = 0; i < rest; i++) {
            for (char c : c1) {
                if (c == c2[index]) {
                    index++;
                    if (index == s2.length()) {
                        ans++;
                        index = 0;
                    }
                }
            }
        }
        //S1 包含 ans 个 s2，那么就包含 ans / n2 个 S2
        return ans / n2;
    }
}
