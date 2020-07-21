package leet;

import java.util.*;

public class Solution347 {
    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 2, 3};
        int k = 2;
        Solution347 solution347 = new Solution347();
        System.out.println(Arrays.toString(solution347.topKFrequent2(a, k)));
    }

    /**
     * 堆方法
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> out = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (map.get(o1) > map.get(o2)) {
                    return -1;
                } else if (map.get(o1) < map.get(o2)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        if (nums.length == 0) return null;
        for (int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        queue.addAll(map.keySet());

        while (k > 0) {
            out.add(queue.remove());
            k--;
        }
        return out;
    }

    /**
     * 部分快排
     */
    Map<Integer, Integer> map = new HashMap<>();

    public int[] topKFrequent2(int[] nums, int k) {
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> topK = getTopK(map, k);

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = topK.get(i);
        }
        return res;
    }

    private List<Integer> getTopK(Map<Integer, Integer> map, int k) {
        if (map.size() == 0) {
            return new ArrayList<>();
        }
        if (map.size() <= k) {
            return new ArrayList<>(map.keySet());
        }

        ArrayList<Integer> keys = new ArrayList<>(map.keySet());
        quickSearch(keys, 0, keys.size() - 1, k);
        return keys.subList(0, k);
    }

    private void quickSearch(List<Integer> keys, int left, int right, int k) {
        int m = parathion(keys, left, right);
        if (m == k) {
            return;
        }

        if (m < k) {
            quickSearch(keys, m + 1, right, k);
        } else {
            quickSearch(keys, left, m - 1, k);
        }
    }

    private int parathion(List<Integer> keys, int low, int high) {
        if (low >= high)return low;
        int left = low;
        int right = high + 1;
        int v = map.get(keys.get(low));

        while (true) {
            while (map.get(keys.get(++left)) > v) {
                if (left == high) {
                    break;
                }
            }
            while (map.get(keys.get(--right)) < v) {
                if (right == low) {
                    break;
                }
            }
            if (left >= right) {
                break;
            }
            int temp = keys.get(left);
            keys.set(left, keys.get(right));
            keys.set(right, temp);
        }

        int temp = keys.get(low);
        keys.set(low, keys.get(right));
        keys.set(right, temp);
        return right;
    }

    /**
     * 桶排序
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent3(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        ArrayList<Integer>[] lists = new ArrayList[nums.length + 1];
        for (Integer key : map.keySet()) {
            int idx = map.get(key);
            if (lists[idx] == null) {
                lists[idx] = new ArrayList<>();
            }
            lists[idx].add(key);
        }
        // 倒序遍历数组获取出现顺序从大到小的排列
        for (int i = lists.length - 1; i >= 0; i--) {
            if (lists[i] == null) {
                continue;
            }
            if (res.size() == k) {
                break;
            }
            res.addAll(lists[i]);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
