package leet;
import leet.ListNode;
/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Solution21 {
    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        ListNode l1_ = l1;
        l1.next = new ListNode(2);l1 = l1.next;
        l1.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        ListNode l2_ = l2;
        l2.next = new ListNode(3);l2 = l2.next;
        l2.next = new ListNode(4);

        ListNode ls = mergeTwoLists(l1_, l2_);
        while (ls != null){
            System.out.println(ls.val);
            ls = ls.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode out;
        ListNode outCopy;

        if (l1 == null && l2 == null)return null;
        if (l1 == null && l2 != null)return l2;
        else if (l1 != null && l2 == null)return l1;

        if (l1.val < l2.val){
            out = l1;
            l1 = l1.next;
        }
        else {
            out = l2;
            l2 = l2.next;
        }
        outCopy = out;

        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                out.next = l1;
                l1 = l1.next;
                out = out.next;
            }
            else {
                out.next = l2;
                l2 = l2.next;
                out = out.next;
            }
        }
        if (l1 == null){
            out.next = l2;
        }
        if (l2 == null){
            out.next = l1;
        }
        return outCopy;
    }
}


