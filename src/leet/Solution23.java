package leet;

import leet.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class Solution23 {
    public static void main(String[] args) {
    }

    /**
     * 分治法
     *
     * @param lists
     * @return
     */

    public static ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) return null;
        if (n == 1) return lists[0];
        return mergeKListsDis(lists, 0, lists.length - 1);
    }

    private static ListNode mergeKListsDis(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];

        int mid = (l + r) >>> 1;
        ListNode leftNode = mergeKListsDis(lists, l, mid);
        ListNode rightNode = mergeKListsDis(lists, mid + 1, r);

        return merge(leftNode, rightNode);
    }

    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0)return null;
        if (lists.length == 1)return lists[0];

        return mergeK(lists, 0, lists.length-1);
    }

    private static ListNode mergeK(ListNode[] lists, int l, int r) {
        if (l == r)return lists[r];
        int mid = (l+r) >>> 1;

        ListNode left = mergeK(lists, l, mid - 1);
        ListNode right = mergeK(lists, mid + 1, r);

        return merge(left, right);
    }

    private static ListNode merge2List(ListNode a, ListNode b) {
        if (a == null)return b;
        if (b == null)return a;

        if (a.val < b.val){
            a.next = merge2List(a.next, b);
            return a;
        }
        else {
            b.next = merge2List(a, b.next);
            return b;
        }
    }

    private static ListNode merge(ListNode a, ListNode b) {
        ListNode head = new ListNode(0);
        ListNode mov = head;
        while (a != null && b != null){
            if (a.val <= b.val){
                mov.next = a;
                a = a.next;
            }
            else {
                mov.next = b;
                b = b.next;
            }
            mov = mov.next;
        }
        if (a != null)mov.next = a;
        if (b != null)mov.next = b;
        return head.next;
    }
}


