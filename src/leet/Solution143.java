package leet;

import java.util.Stack;

public class Solution143 {
    public static void main(String[] args){
        ListNode head = new ListNode(1);ListNode head_c = head;
        head.next = new ListNode(2);head = head.next;
        head.next = new ListNode(3);head = head.next;
        head.next = new ListNode(4);head = head.next;
        head.next = new ListNode(5);

        reorderList(head_c);

        int aa = 0;
    }

    public static void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode mov = head;
        while (mov != null){
            stack.push(mov);
            mov = mov.next;
        }
        mov = head;

        ListNode tmp = null;
        int count = 0;
        int nums = stack.size();
        while (count != nums){
            if (count % 2 == 0) {
                tmp = mov.next;
                mov.next = stack.pop();
            }
            if (count % 2 == 1){
                mov.next = tmp;
            }

            if (count != nums-1) {
                mov = mov.next;
            }else {
                mov.next = null;
            }
            count++;
        }
    }
}
