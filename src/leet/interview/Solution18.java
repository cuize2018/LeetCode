package leet.interview;

import leet.ListNode;

import java.util.List;

public class Solution18 {
    public static void main(String[] args) {

    }

    public static ListNode deleteNode(ListNode head, int val) {
        ListNode mov = head;
        ListNode prev = null;

        while (mov != null) {
            if (mov.val == val) {
                if (prev != null) prev.next = mov.next;
                else head = mov.next;
                break;
            }
            prev = mov;
            mov = mov.next;
        }
        return head;
    }
}
