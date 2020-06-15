package leet.interview;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution16_25 {
    public static void main(String[] args) {

    }


}

class LRUCache {
    LinkedHashMap<Integer, Integer> map;
    int totalSize;

    public LRUCache(int capacity) {
        map = new LinkedHashMap<>(16, 0.75F, true);
        totalSize = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        return map.get(key);
    }

    public void put(int key, int value) {
        if (!map.containsKey(key) && map.size() == totalSize) {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            Map.Entry<Integer, Integer> entry = iterator.next();
            map.remove(entry.getKey());
        }
        map.put(key, value);
    }
}

class LRUCache2 {
    int cap;
    HashMap<Integer, MapNode> map;
    MapNode head;
    MapNode tail;

    public LRUCache2(int capacity) {
        cap = capacity;
        map = new HashMap<>();
        head = new MapNode(0, 0);
        tail = new MapNode(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        MapNode node = map.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        if (map.size() == cap) {
            remove(head.next);
        }
        add(new MapNode(key, value));
    }

    public void remove(MapNode node) {
        map.remove(node.key);

        MapNode prev = node.prev;
        MapNode next = node.next;
        prev.next = next;
        next.prev = prev;

        node.prev = null;
        node.next = null;
    }

    public void add(MapNode node) {
        map.put(node.key, node);
        MapNode tailPrev = tail.prev;

        tailPrev.next = node;
        node.prev = tailPrev;
        node.next = tail;
        tail.prev = node;
    }
}

class MapNode {
    int key;
    int val;
    MapNode prev;
    MapNode next;

    public MapNode(int k, int v) {
        key = k;
        val = v;
        prev = null;
        next = null;
    }
}


