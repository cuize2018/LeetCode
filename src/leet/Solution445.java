package leet;

import javax.xml.ws.soap.Addressing;
import java.util.Stack;

public class Solution445 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);ListNode l1_h = l1;
        l1.next = new ListNode(2);l1 = l1.next;
        l1.next = new ListNode(4);l1 = l1.next;
        l1.next = new ListNode(3);l1 = l1.next;

        ListNode l2 = new ListNode(5);ListNode l2_h = l2;
        l2.next = new ListNode(6);l2 = l2.next;
        l2.next = new ListNode(4);l2 = l2.next;

        ListNode out = addTwoNumbers(l1_h,l2_h);
        out.PrintListNode();

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.val == 0)return l2;
        if (l2.val == 0)return l1;

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        ListNode tmp = l1;
        while (tmp != null){
            stack1.push(tmp.val);
            tmp = tmp.next;
        }
        tmp = l2;
        while (tmp != null){
            stack2.push(tmp.val);
            tmp = tmp.next;
        }

        Stack<Integer> res;
        if (stack1.size() >= stack2.size()){
            res = Add(stack1, stack2);
        }
        else {
            res = Add(stack2, stack1);
        }

        ListNode out = new ListNode(res.pop());ListNode out_h = out;
        while (!res.isEmpty()){
            out.next = new ListNode(res.pop());
            out = out.next;
        }
        return out_h;
    }


    public static Stack<Integer> Add(Stack<Integer> longer, Stack<Integer> shorter){
        Stack<Integer> final_val = new Stack<>();
        int common_len = Math.min(longer.size(), shorter.size());
        int add = 0;
        for (int i = 0;i < common_len;i++){
            int tmp_val =  longer.pop()+shorter.pop() + add;
            if (tmp_val < 10){
                final_val.push(tmp_val);
                add = 0;
            }
            else {
                final_val.push(tmp_val-10);
                add = 1;
            }
        }
        while (longer.size() > 0){
            int tmp_val = longer.pop() + add;
            if (tmp_val < 10){
                final_val.push(tmp_val);
                add = 0;
            }
            else {
                final_val.push(tmp_val-10);
                add = 1;
            }
        }
        if (add != 0)final_val.push(add);
        return final_val;
    }
}
