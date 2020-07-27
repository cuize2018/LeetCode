package leet.interview;

import leet.ListNode;

public class Solution02_05 {
    public static void main(String[] args) {

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode mov = null;
        ListNode res = null;
        int add = 0;
        while (l1 != null && l2 != null) {
            int a = l1.val;
            int b = l2.val;
            int sum = a + b + add;
            int v = sum - 10 >= 0 ? sum - 10 : sum;
            if (mov == null) {
                mov = new ListNode(v);
                res = mov;
            } else {
                mov.next = new ListNode(v);
                mov = mov.next;
            }
            add = sum - 10 >= 0 ? 1 : 0;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null && add != 0) {
            int sum = l1.val + add;
            int v = sum - 10 >= 0 ? sum - 10 : sum;
            mov.next = new ListNode(v);
            mov = mov.next;
            add = sum - 10 >= 0 ? 1 : 0;
            l1 = l1.next;
        }
        if (l1 != null) mov.next = l1;

        while (l2 != null && add != 0) {
            int sum = l2.val + add;
            int v = sum - 10 >= 0 ? sum - 10 : sum;
            mov.next = new ListNode(v);
            mov = mov.next;
            add = sum - 10 >= 0 ? 1 : 0;
            l2 = l2.next;
        }
        if (l2 != null) mov.next = l2;
        if (add != 0) mov.next = new ListNode(add);
        return res;
    }
}
