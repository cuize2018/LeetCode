package leet;
import leet.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Solution25 {
    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = a;
        a.next = new ListNode(2);a=a.next;
        a.next = new ListNode(3);a=a.next;
        a.next = new ListNode(4);a=a.next;
        a.next = new ListNode(5);a=a.next;
        a.next = new ListNode(6);

        ListNode out = reverseKGroup2(b, 3);
        while (out != null){
            System.out.println(out.val);
            out = out.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)return null;
        if (head.next == null)return head;
        if (k == 1)return head;

        ListNode headCopy = head;
        ListNode mov = head;
        ListNode last = mov;

        int count2 = 0;
        ListNode tmp2 = mov;
        while (tmp2 != null) {
            count2++;
            tmp2 = tmp2.next;
        }
        if (count2 < k){
            return head;
        }

        int count = 1;
        ListNode first = mov;
        last = mov;
        mov = mov.next;
        last.next = null;

        while (mov != null && count < k) {
            ListNode tmp = mov;
            mov = mov.next;
            tmp.next = last;
            last = tmp;
            count++;
        }
        first.next = mov;
        headCopy = last;


        while (mov != null) {
            int count1 = 0;
            ListNode tmp1 = mov;
            while (tmp1 != null) {
                count1++;
                tmp1 = tmp1.next;
            }

            if (count1 >=k) {
                count = 1;
                ListNode first_ = mov;
                last = mov;
                mov = mov.next;
                last.next = null;

                while (mov != null && count < k) {
                    ListNode tmp = mov;
                    mov = mov.next;
                    tmp.next = last;
                    last = tmp;
                    count++;
                }
                first_.next = mov;
                first.next = last;
                first = first_;
            }
            else {
                break;
            }
        }
        return headCopy;
    }

    public static ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null)return null;
        if (head.next == null)return head;
        if (k == 1)return head;

        ListNode headCopy = head;
        ListNode mov = head;
        ListNode last = mov;
        ListNode first_last = mov;

        int len = CalLen(mov);
        if (len < k){
            return head;
        }

        //第一次执行
        headCopy = ReverseSlice(mov, last, null, k,true);
        mov = mov.next;//mov传入函数的是地址，函数结束后根据地址确定mov位置，因为是翻转链表，所以mov地址一定再下一个片段前

        //后续执行
        while (mov != null) {
            int lenRest = CalLen(mov);
            if (lenRest >= k) {
                first_last = ReverseSlice(mov, mov, first_last, k,false);
                mov = mov.next;
            }
            else {
                break;
            }
        }
        return headCopy;
    }

    private static int CalLen(ListNode n){
        int lenRest = 0;//剩余长度
        ListNode tmp1 = n;
        while (tmp1 != null) {
            lenRest++;
            tmp1 = tmp1.next;
        }
        return lenRest;
    }

    private static ListNode ReverseSlice(ListNode now, ListNode last, ListNode first_last, int k, boolean IfFirst){
        int count = 1;
        ListNode first_this = now;
        now = now.next;
        last.next = null;

        while (count < k) {
            ListNode tmp = now;
            now = now.next;
            tmp.next = last;
            last = tmp;
            count++;
        }
        first_this.next = now;
        if (first_last != null) {
            first_last.next = last;
            first_last = first_this;
        }
        if (IfFirst) return last;
        return first_last;
    }

}
