package leet;

import java.util.*;

public class LRUCache146 {
    private HashMap<Integer, DoubleNode> map;
    private int cap;
    private DoubleNode head;
    private DoubleNode tail;

    public static void main(String[] args){
        LRUCache146 cache = new LRUCache146(2);
        int t = -5;
        t = cache.get(2);
        cache.put(2, 6);
        t = cache.get(1);
        cache.put(1, 5);
        cache.put(1, 2);
        t = cache.get(1);
        t = cache.get(2);

        int o = 0;
    }

    public LRUCache146(int capacity) {
        cap = capacity;
        map = new HashMap<>();
        head = new DoubleNode(0,0);
        tail = new DoubleNode(0,0);

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)){
           DoubleNode node = map.get(key);
           remove(node);
           add(node);

           return node.val;
        }
        else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            remove(map.get(key));
        }

        if (map.size() == cap){
            remove(head.next);
        }
        add(new DoubleNode(key,value));
    }

    private void add(DoubleNode node){
        map.put(node.key, node);

        DoubleNode tailPre = tail.pre;

        tailPre.next = node;
        node.next = tail;

        node.pre = tailPre;
        tail.pre = node;
    }

    private void remove(DoubleNode node){
        map.remove(node.key);

        DoubleNode preNode = node.pre;
        DoubleNode nextNode = node.next;

        preNode.next = nextNode;
        nextNode.pre = preNode;
    }
}

class DoubleNode{
    int key;
    int val;
    DoubleNode pre;
    DoubleNode next;

    DoubleNode(int key, int value){
        this.key = key;
        this.val = value;
        this.pre = null;
        this.next = null;
    }
}
