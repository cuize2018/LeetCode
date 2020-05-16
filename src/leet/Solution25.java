package leet;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Solution25 {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = a;
        a.next = new ListNode(2);
        a = a.next;
        a.next = new ListNode(3);
        a = a.next;
        a.next = new ListNode(4);
        a = a.next;
        a.next = new ListNode(5);
        a = a.next;
//        a.next = new ListNode(6);

        ListNode out = reverseKGroup(b, 2);
        while (out != null) {
            System.out.println(out.val);
            out = out.next;
        }
    }

    private static int CalLen(ListNode n) {
        int lenRest = 0;//剩余长度
        ListNode tmp1 = n;
        while (tmp1 != null) {
            lenRest++;
            tmp1 = tmp1.next;
        }
        return lenRest;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        int len = CalLen(head);
        if (len < k) return head;
        int groupNum = len / k;

        ListNode mov = head;
        ListNode prevGroupLastNode = null;//prevGroupLastNode为上一组反转后的最后一个节点，exp: 上一组[1,2]->[2,1], 此时为1节点
        for (int i = 1; i <= groupNum; i++) {
            ListNode groupLastNode = reverseOneGroup(mov, k);//返回组内反转之前的最后一个节点
            if (i == 1) {
                head = groupLastNode;//如果是第一组，则重新设置头节点
            } else {
                prevGroupLastNode.next = groupLastNode;
            }
            prevGroupLastNode = mov;//更新prevGroupLastNode
            mov = mov.next;
        }
        return head;
    }

    //翻转一组
    //exp [1,2,3,4,5,6,7]
    private static ListNode reverseOneGroup(ListNode startNode, int k) {
        ListNode mov = startNode;
        ListNode prev = null;
        for (int i = 0; i < k; i++) {
            ListNode next = mov.next;
            mov.next = prev;
            prev = mov;
            mov = next;
        }
        startNode.next = mov;//startNode = 1, mov = 4
        return prev;//prev = 3
    }

}
