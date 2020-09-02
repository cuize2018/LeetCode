package leet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution146 {
    public static void main(String[] args) {
        LRUCache l = new LRUCache(2);
        l.put(2, 1);
        l.put(1, 1);
        l.put(2, 3);
        l.put(4, 1);
        l.get(4);
        l.get(3);
        l.get(2);
        l.get(1);
        l.put(5, 5);
    }
}

class LRUCache {
    LinkedHashMap<Integer, Integer> cache;
    int maxSize;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<>(100, 0.75f, true);
        maxSize = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (!cache.containsKey(key)) {
            if (cache.size() == maxSize) {
                Map.Entry<Integer, Integer> map = cache.entrySet().iterator().next();
                Integer removeKey = map.getKey();
                cache.remove(removeKey);
            }
        }
        cache.put(key, value);
    }
}

class LRUCache2 {
    Map<Integer, DoubleNode> map;
    DoubleList list;
    int cap;

    public LRUCache2(int capacity) {
        map = new HashMap<>();
        list = new DoubleList();
        cap = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        DoubleNode node = map.get(key);
        remove(node);
        add(node);

        return node.val;
    }

    private void remove(DoubleNode node) {
        map.remove(node.key);

        DoubleNode pre = node.pre;
        DoubleNode next = node.next;

        pre.next = next;
        next.pre = pre;

        node.next = null;
        node.pre = null;
    }

    private void add(DoubleNode node) {
        map.put(node.key, node);

        DoubleNode tail = list.tail;
        DoubleNode tailPre = tail.pre;

        tailPre.next = node;
        node.next = tail;

        node.pre = tailPre;
        tail.pre = node;
    }

    public void put(int key, int value) {
        DoubleNode node = map.get(key);
        if (map.containsKey(key)) {
            remove(node);
        }
        if (map.size() == cap) {
            remove(list.head.next);
        }
        DoubleNode newNode = new DoubleNode(key, value);
        add(newNode);
    }
}

class DoubleList {
    DoubleNode head;
    DoubleNode tail;

    public DoubleList() {
        head = new DoubleNode(0, 0);
        tail = new DoubleNode(0, 0);
        head.next = tail;
        tail.pre = head;
    }
}





