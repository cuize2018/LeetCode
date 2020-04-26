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
    public static ListNode mergeKLists3(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        int left = 0;
        int right = lists.length - 1;
        int middle = (left + right) >>> 1;

        ListNode leftList = mergeKLists3(Arrays.copyOfRange(lists, 0, middle + 1));
        ListNode rightList = mergeKLists3(Arrays.copyOfRange(lists, middle + 1, right + 1));

        return mergeTwoLists2(leftList, rightList);
    }

    private static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode res;
        ListNode mov;
        if (l1.val < l2.val) {
            mov = l1;
            l1 = l1.next;
        } else {
            mov = l2;
            l2 = l2.next;
        }
        res = mov;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                mov.next = l1;
                l1 = l1.next;
            } else {
                mov.next = l2;
                l2 = l2.next;
            }
            mov = mov.next;
        }

        if (l1 != null) mov.next = l1;
        if (l2 != null) mov.next = l2;
        return res;
    }


}


