package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution138 {
    public static void main(String[] args){
        Node head = new Node(1,null,null);
        Node tmp = new Node(2,null,null);

        head.next = tmp;
        head.random = tmp;
        tmp.random = tmp;

        Node out  = copyRandomList(head);
        int m =  0;
    }

    public static Node copyRandomList(Node head) {
        Node mov = head;
        Node newListMov = new Node();
        Node newHead = newListMov;
        Map<Node, Node> OldNewMap = new HashMap<>();

        if (head == null)return null;
        while (mov != null){
            newListMov.val = mov.val;
            if (mov.next != null) {
                newListMov.next = new Node();
            }
            else {
                newListMov.next = null;
            }

            newListMov.random = null;
            OldNewMap.put(mov,newListMov);

            newListMov = newListMov.next;
            mov=mov.next;
        }
        mov = head;
        newListMov = newHead;
        while (mov != null){
            Node randNode = mov.random;
            newListMov.random = OldNewMap.get(randNode);
            mov = mov.next;
            newListMov = newListMov.next;
        }

        return newHead;
    }
}


class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
