package leet;

import java.util.*;

public class Solution460 {
    public static void main(String[] args) {
        return;
    }
}

class LFUCache {
    Map<Integer, Data> cache;  // 存储缓存的内容
    Map<Integer, LinkedHashSet<Data>> freqMap; // 存储每个频次对应的双向链表
    int size;
    int capacity;
    int min; // 存储当前最小频次

    public LFUCache(int capacity) {
        cache = new HashMap<> (capacity);
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Data data = cache.get(key);
        if (data == null) {
            return -1;
        }
        freqInc(data);
        return data.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Data data = cache.get(key);
        if (data != null) {
            data.value = value;
            freqInc(data);
        } else {
            if (size == capacity) {
                Data deadData = removeNode();
                cache.remove(deadData.key);
                size--;
            }
            Data newData = new Data(key, value);
            cache.put(key, newData);
            addNode(newData);
            size++;
        }
    }

    void freqInc(Data data) {
        // 从原freq对应的链表里移除, 并更新min
        int freq = data.freq;
        LinkedHashSet<Data> set = freqMap.get(freq);
        set.remove(data);
        if (freq == min && set.size() == 0) {
            min = freq + 1;
        }
        // 加入新freq对应的链表
        data.freq++;
        LinkedHashSet<Data> newSet = freqMap.get(freq + 1);
        if (newSet == null) {
            newSet = new LinkedHashSet<>();
            freqMap.put(freq + 1, newSet);
        }
        newSet.add(data);
    }

    void addNode(Data data) {
        LinkedHashSet<Data> set = freqMap.get(1);
        if (set == null) {
            set = new LinkedHashSet<>();
            freqMap.put(1, set);
        }
        set.add(data);
        min = 1;
    }

    Data removeNode() {
        LinkedHashSet<Data> set = freqMap.get(min);
        Data deadData = set.iterator().next();
        set.remove(deadData);
        return deadData;
    }
}

class Data {
    int key;
    int value;
    int freq = 1;

    public Data() {}

    public Data(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
