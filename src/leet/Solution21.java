package leet;

import leet.ListNode;

import java.util.List;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Solution21 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l1_ = l1;
        l1.next = new ListNode(2);
        l1 = l1.next;
        l1.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        ListNode l2_ = l2;
        l2.next = new ListNode(3);
        l2 = l2.next;
        l2.next = new ListNode(4);

        ListNode ls = mergeTwoLists(l1_, l2_);
        while (ls != null) {
            System.out.println(ls.val);
            ls = ls.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode res = null;
        ListNode resHead = null;

        if (l1.val < l2.val){
            res = l1;
            resHead = l1;
            l1 = l1.next;
        }
        else {
            res = l2;
            resHead = l2;
            l2 = l2.next;
        }

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val){
                res.next = l1;
                l1 = l1.next;
            }
            else {
                res.next = l2;
                l2 = l2.next;
            }
            res = res.next;
        }
        if (l1 != null)res.next = l1;
        if (l2 != null)res.next = l2;
        return resHead;
    }
}


