package leet.interview;

import leet.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution02_01 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode headc = head;
        head.next = new ListNode(1);
        head = head.next;
        head.next = new ListNode(1);
        head = head.next;
        head.next = new ListNode(1);
        head = head.next;
        head.next = new ListNode(2);
        head = head.next;
//        head.next = new ListNode(1);head = head.next;

        ListNode res = removeDuplicateNodes2(headc);
        res.PrintListNode();
    }

    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) return null;

        ListNode curr = head;
        ListNode res = curr;
        ListNode mov = curr.next;

        Set<Integer> set = new HashSet<>();
        set.add(curr.val);

        if (mov == null) return curr;

        while (curr != null && mov != null) {
            while (mov != null && mov.val == curr.val) {
                mov = mov.next;
            }
            if (mov != null) {
                if (!set.contains(mov.val)) {
                    curr.next = mov;
                    set.add(mov.val);
                    curr = curr.next;
                }
                mov = mov.next;
            }
        }
        if (curr != null) {
            curr.next = null;
        }
        return res;
    }

    public static ListNode removeDuplicateNodes2(ListNode head) {
        if (head == null)return null;

        ListNode fakeHead = new ListNode(-1);
        ListNode pre = fakeHead;
        Set<Integer> set = new HashSet<>();
        fakeHead.next = head;

        while (pre.next != null){
            if (!set.contains(pre.next.val)){
                set.add(pre.next.val);
                pre = pre.next;
            }
            else {
                pre.next = pre.next.next;
            }
        }
        return fakeHead.next;
    }
}
