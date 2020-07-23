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

        quickSort(temp, 0, temp.length-1, k);
        Integer v = map.get(temp[k - 1]);
        int j = k;
        while (j < temp.length && map.get(temp[j]).equals(v)){
            j++;
        }

        for (int l = 0; l < j; l++) {
            res.add(temp[l]);
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
        if (m == k - 1){
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
        int i = low;
        int j = high+1;
        String str = temp[low];
        int v = map.get(str);
        while (true){
            while (map.get(temp[++i]) > v){
                if (i == high)break;
            }
            while (map.get(temp[--j]) < v){
                if (j == low)break;
            }
            if (i >= j)break;
            swap(temp, i, j);
        }
        swap(temp, low, j);
        return j;
    }

    private void swap(String[] temp, int i, int j) {
        String a = temp[i];
        temp[i] = temp[j];
        temp[j] = a;
    }
}
