package leet;

import java.util.List;
import java.util.Stack;

public class Solution206 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);ListNode mov = head;
        mov.next = new ListNode(2);

        ListNode out = reverseList2(head);
        int a = 0;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null)return null;
        Stack<ListNode> stack = new Stack<>();

        while (head != null){
            stack.push(head);
            head = head.next;
        }

        ListNode newHead = stack.pop();
        ListNode mov = newHead;

        while (!stack.isEmpty()){
            mov.next = stack.pop();
            mov = mov.next;
        }
        mov.next = null;

        return newHead;

    }

    public static ListNode reverseList2(ListNode head) {
        if (head == null)return null;

        ListNode mov = head;
        ListNode last = null;
        ListNode next;

        while (mov != null){
            next = mov.next;
            mov.next = last;
            last = mov;
            mov = next;
        }
        return last;
    }


}
