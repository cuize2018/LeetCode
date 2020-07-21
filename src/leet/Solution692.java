package leet;

import java.util.*;

public class Solution692 {
    Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "mmm"};
        int k = 1;

        Solution692 solution692 = new Solution692();
        List<String> list = solution692.topKFrequent(words, k);
        System.out.println(list);
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0)+1);
        }

        if (map.keySet().size() <= k){
            res = new ArrayList<>(map.keySet());
            res.sort((x, y) -> {
                if (map.get(x) < map.get(y))return 1;
                else if (map.get(x) > map.get(y))return -1;
                else {
                    return x.compareTo(y);
                }
            });
            return res;
        }

        String[] temp = new String[map.keySet().size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            temp[i++] = entry.getKey();
        }

        quickSort(temp, 0, temp.length-1, temp.length - k);
        Integer v = map.get(temp[temp.length - k]);
        int j = temp.length - k - 1;
        while (j >= 0 && map.get(temp[j]).equals(v)){
            j--;
        }

        for (int m = temp.length-1; m >= j+1; m--) {
            res.add(temp[m]);
        }
        res.sort((x, y) -> {
            if (map.get(x) < map.get(y))return 1;
            else if (map.get(x) > map.get(y))return -1;
            else {
                return x.compareTo(y);
            }
        });

        return res.subList(0, k);
    }

    private void quickSort(String[] temp, int low, int high, int k) {
        int m = paration(temp, low, high);
        if (m == k){
            return;
        }

        if (m < k){
            quickSort(temp, m+1, high, k);
        }
        else {
            quickSort(temp, low, m - 1, k);
        }
    }

    private int paration(String[] temp, int low, int high) {
        if (low == high)return high;

        int i = low;
        int j = high+1;
        String str = temp[low];
        while (true){
            while (compare(temp[++i], str) < 0){
                if (i == high)break;
            }
            while (compare(temp[--j], str) > 0){
                if (j == low)break;
            }
            if (i >= j)break;
            swap(temp, i, j);
        }
        swap(temp, low, j);
        return j;
    }

    private int compare(String x, String y){
        if (map.get(x) < map.get(y))return -1;
        else if (map.get(x) > map.get(y))return 1;
        else {
            return x.compareTo(y);
        }
    }

    private void swap(String[] temp, int i, int j) {
        String a = temp[i];
        temp[i] = temp[j];
        temp[j] = a;
    }
}
