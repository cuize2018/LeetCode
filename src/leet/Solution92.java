package leet;

import java.util.Stack;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL

 */
public class Solution92 {
    public static void main(String[] args){
        ListNode l = new ListNode(1);
        ListNode h = l;
        l.next = new ListNode(2);l=l.next;
//        l.next = new ListNode(3);l=l.next;
//        l.next = new ListNode(4);l=l.next;
//        l.next = new ListNode(5);l=l.next;

        ListNode out = reverseBetween(h,1,2);
        out.PrintListNode();
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode head_copy = head;
        ListNode mov = head;
        int count = 1;

        ListNode start_pre = head;
        ListNode end_next = null;

        Stack<ListNode> stack = new Stack<>();
        boolean start_flag = false;
        while (mov != null){
            if ((count == m-1 && m !=1) || (count == m && m == 1)){
                start_flag =  true;

                if (m !=1) start_pre = mov;
                else {
                    start_pre = null;
                    stack.push(mov);
                }
            }
            if (count == n){
                start_flag = false;
                end_next = mov.next;
                stack.push(mov);
            }

            mov = mov.next;
            count++;
            if (start_flag) stack.push(mov);
        }

        mov = start_pre;
        while (stack.size() > 0){
            if (mov!=null) {
                mov.next = stack.pop();
                mov = mov.next;
            }
            else {
                mov = stack.pop();
                head_copy = mov;
            }
        }
        if (mov != null) {
            mov.next = end_next;
        }

        return head_copy;
    }
}
