package leet;

import java.util.LinkedList;
import java.util.ListIterator;

public class Solution706 {
    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1);
        myHashMap.put(2, 2);
        myHashMap.get(1);
    }
}

class MyHashMap {
    private final LinkedList<Pair>[] nodes;
    private final int size = 9999;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        nodes = new LinkedList[size];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int idx = key % size;
        if (nodes[idx] == null) {
            nodes[idx] = new LinkedList<>();
        }
        LinkedList<Pair> list = nodes[idx];
        ListIterator<Pair> iterator = list.listIterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getX() == key) {
                iterator.set(new Pair(key, value));
                found = true;
                break;
            }
        }
        if (!found) nodes[idx].add(new Pair(key, value));
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int idx = key % size;
        if (nodes[idx] == null) {
            return -1;
        }

        for (Pair pair : nodes[idx]) {
            if (pair.getX() == key) {
                return pair.getY();
            }
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int idx = key % size;
        if (nodes[idx] == null) return;

        LinkedList<Pair> list = nodes[idx];
        ListIterator<Pair> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getX() == key) {
                iterator.remove();
            }
        }
    }
}
