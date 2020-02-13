package leet;

import java.util.*;

public class Solution380 {
    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        boolean param_1 = obj.insert(1);
        boolean param_2 = obj.insert(2);
        boolean param_3 = obj.insert(3);
        boolean s = obj.remove(1);
        int param_4 = obj.getRandom();

        int a = 0;
    }


}

class RandomizedSet {
    private HashMap<Integer, Integer> map;
    private List<Integer> list;

    /**
     * 哈希表存储存储值到索引的映射。
     */
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new LinkedList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)){
            list.add(val);
            map.put(val,list.size()-1);
            return true;
        }
        return false;
    }

    /**
     * 在哈希表中查找要删除元素的索引。
     * 将要删除元素与最后一个元素交换。
     * 删除最后一个元素。
     * 更新哈希表中的对应关系。
     */
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)){
            int idx = map.get(val);
            Collections.swap(list, idx, list.size()-1);
            map.put(list.get(idx), idx);

            list.remove(list.size()-1);
            map.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        return list.get(index);
    }

}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
