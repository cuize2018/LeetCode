package leet.interview;

import java.util.HashMap;
import java.util.Map;

public class Solution35 {
    public static void main(String[] args) {

    }

    public Node copyRandomList(Node head) {
        if (head == null)return null;

        Map<Node, Node> map = new HashMap<>();
        Node mov_old = head;

        Node list = new Node(-1);
        Node mov = list;

        while (mov_old != null){
            mov.val = mov_old.val;

            if (mov_old.next != null){
                mov.next = new Node(-1);
            }
            map.put(mov_old, mov);

            mov = mov.next;
            mov_old = mov_old.next;
        }
        mov_old = head;
        mov = list;

        while (mov_old!=null){
            mov.random = map.get(mov_old.random);
            mov = mov.next;
            mov_old = mov_old.next;
        }
        return list;
    }
}

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }
};

