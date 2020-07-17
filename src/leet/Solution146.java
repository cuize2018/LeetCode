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

class LRUCache3 {
    Map<Integer, CacheNode> map = new HashMap<>();
    int capacity;
    DoubleLinkedList cache = new DoubleLinkedList();
    public LRUCache3(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)){
            CacheNode node = map.get(key);
            put(key, node.val);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        CacheNode node = new CacheNode(key, value);
        if (map.containsKey(key)){
            cache.remove(map.get(key));
        }
        else {
            if (cache.size == capacity){
                CacheNode lastNode = cache.removeLast();
                map.remove(lastNode.key);
            }
        }
        cache.addFirst(node);
        map.put(key, node);
    }
}

class DoubleLinkedList {
    CacheNode head = null;
    CacheNode tail = null;
    int size = 0;
    public DoubleLinkedList(){
        head = new CacheNode(0,0);
        tail = new CacheNode(0,0);

        head.next = tail;
        tail.pre = head;

    }

    public void addFirst(CacheNode node){
        node.next = head.next;
        head.next.pre = node;

        node.pre = head;
        head.next = node;
        size++;
    }

    public CacheNode removeLast(){
        if (tail.pre == head)return null;
        CacheNode node = tail.pre;
        remove(node);
        return node;
    }

    public void remove(CacheNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        size--;
    }
}

class CacheNode{
    int key;
    int val;
    CacheNode pre = null;
    CacheNode next = null;

    public CacheNode(int x, int y){
        key = x;
        val = y;
    }
}