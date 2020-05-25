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
    Map<Integer, LinkedHashMapNode> map;
    int maxSize;
    DoubleLinkedList cache;

    public LRUCache2(int capacity) {
        map = new HashMap<>();
        maxSize = capacity;
        cache = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        int val = map.get(key).value;
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        LinkedHashMapNode node = new LinkedHashMapNode(key, value);
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
        } else {
            if (cache.getSize() == maxSize) {
                LinkedHashMapNode lastNode = cache.removeLast();
                map.remove(lastNode.key);
            }
        }
        cache.addFirst(node);
        map.put(key, node);
    }
}

class DoubleLinkedList {
    private LinkedHashMapNode head;
    private LinkedHashMapNode tail;
    private int size;

    public DoubleLinkedList() {
        head = new LinkedHashMapNode(0, 0);
        tail = new LinkedHashMapNode(0, 0);

        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addFirst(LinkedHashMapNode node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
        size++;
    }

    public void remove(LinkedHashMapNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    public int getSize() {
        return size;
    }

    public LinkedHashMapNode removeLast() {
        if (tail.prev == head) return null;
        LinkedHashMapNode lastNode = tail.prev;
        remove(lastNode);
        return lastNode;
    }
}


class LinkedHashMapNode {
    public int key;
    public int value;
    public LinkedHashMapNode prev;
    public LinkedHashMapNode next;

    public LinkedHashMapNode(int k, int v) {
        key = k;
        value = v;
    }
}