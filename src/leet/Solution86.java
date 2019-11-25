package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution86 {
    public static void main(String[] args){
        ListNode l = new ListNode(1);
        ListNode head = l;
//        l.next = new ListNode(4);l=l.next;
//        l.next = new ListNode(3);l=l.next;
//        l.next = new ListNode(2);l=l.next;
//        l.next = new ListNode(5);l=l.next;
//        l.next = new ListNode(2);l=l.next;

        ListNode out = partition(head, 1);
        int c = 0;
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        List<Integer> small = new ArrayList<>();
        List<Integer> large = new ArrayList<>();

        ListNode mov = head;
        while (mov != null){
            if (mov.val < x){
                small.add(mov.val);
            }
            else if (mov.val >= x){
                large.add(mov.val);
            }
            mov = mov.next;
        }
        mov = head;

        for (int val : small){
            mov.val = val;
            mov = mov.next;
        }
//        mov.val = x;
//        mov = mov.next;

        for (int val : large){
            mov.val = val;
            mov = mov.next;
        }

        return head;
    }
}
