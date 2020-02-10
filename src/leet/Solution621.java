package leet;

import java.util.*;

public class Solution621 {
    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        System.out.println(leastInterval(tasks,n));
    }

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        for (char c : tasks){
            if (map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }
            else {
                map.put(c, 1);
            }
        }

        List<Character> all_task = new ArrayList<>(map.keySet());
        Set<Character> delete_task = new HashSet<>();

        while (!map.isEmpty()) {
            all_task.sort(new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    return Integer.compare(map.get(o2), map.get(o1));
                }
            });

            int length = all_task.size();
            for (int i = 0;i < n+1;i++) {
                if (i < length) {
                    char task = all_task.get(i);
                    if (map.get(task) > 1) {
                        map.put(task, map.get(task) - 1);
                    } else {
                        map.remove(task);
                        delete_task.add(task);
                    }
                }
                count++;
                if (map.isEmpty())break;
            }
            all_task.removeAll(delete_task);
            delete_task.clear();
        }
        return count;
    }

    /**
     * 由于相同的任务之间必须有 n 的冷却时间，所以我们可以想到按照任务的数量来安排它们，
     * 即一种任务的出现次数越多，我们就越早地安排。
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        for (char c : tasks){
            if (map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }
            else {
                map.put(c, 1);
            }
        }

        PriorityQueue<Character> all_task = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Integer.compare(map.get(o2), map.get(o1));
            }
        });
        all_task.addAll(map.keySet());
        List<Character> list = new ArrayList<>();

        while (!map.isEmpty()) {
            int length = all_task.size();
            for (int i = 0;i < n+1;i++) {
                if (i < length) {
                    char task = all_task.remove();
                    if (map.get(task) > 1) {
                        map.put(task, map.get(task) - 1);
                        list.add(task);
                    } else {
                        map.remove(task);
                    }
                }
                count++;
                if (map.isEmpty())break;
            }
            all_task.addAll(list);
            list.clear();
        }
        return count;
    }

    public static int leastInterval3(char[] tasks, int n) {
        int[] map = new int[26];
        int count = 0;
        for (char c : tasks){
            map[c -'A']++;
        }
        Arrays.sort(map);

        while (map[25] != 0) {
            for (int i = 0;i < n+1;i++) {
                if (map[25] == 0)break;
                if (i < 26 && map[25-i] > 0){
                    map[25-i]--;
                }
                count++;
            }
            Arrays.sort(map);
        }
        return count;
    }
}
