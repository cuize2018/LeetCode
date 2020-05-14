package leet.interview;

import leet.ListNode;

import java.util.List;

public class Solution52 {
    public static void main(String[] args) {

    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode movA = headA;
        ListNode movB = headB;
        while (movA != movB) {
            movA = movA == null ? headB : movA.next;
            movB = movB == null ? headA : movB.next;
        }
        return movA;
    }
}
