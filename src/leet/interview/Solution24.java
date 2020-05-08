package leet.interview;

import leet.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution24 {
    public static void main(String[] args) {

    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode last = null;
        ListNode mov = head;
        ListNode next = mov.next;

        while (next != null) {
            mov.next = last;
            last = mov;
            mov = next;
            next = mov.next;
        }
        mov.next = last;
        return mov;
    }
}
