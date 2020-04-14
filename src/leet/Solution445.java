package leet;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution445 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(8);
        ListNode l1_h = l1;
        l1.next = new ListNode(9);
        l1 = l1.next;
        l1.next = new ListNode(9);
        l1 = l1.next;
//        l1.next = new ListNode(3);l1 = l1.next;

        ListNode l2 = new ListNode(2);
        ListNode l2_h = l2;
//        l2.next = new ListNode(6);l2 = l2.next;
//        l2.next = new ListNode(4);l2 = l2.next;

        ListNode out = addTwoNumbers2(l1_h, l2_h);
        out.PrintListNode();

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.val == 0) return l2;
        if (l2.val == 0) return l1;

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        ListNode tmp = l1;
        while (tmp != null) {
            stack1.push(tmp.val);
            tmp = tmp.next;
        }
        tmp = l2;
        while (tmp != null) {
            stack2.push(tmp.val);
            tmp = tmp.next;
        }

        Stack<Integer> res;
        if (stack1.size() >= stack2.size()) {
            res = Add(stack1, stack2);
        } else {
            res = Add(stack2, stack1);
        }

        ListNode out = new ListNode(res.pop());
        ListNode out_h = out;
        while (!res.isEmpty()) {
            out.next = new ListNode(res.pop());
            out = out.next;
        }
        return out_h;
    }


    public static Stack<Integer> Add(Stack<Integer> longer, Stack<Integer> shorter) {
        Stack<Integer> final_val = new Stack<>();
        int common_len = Math.min(longer.size(), shorter.size());
        int add = 0;
        for (int i = 0; i < common_len; i++) {
            int tmp_val = longer.pop() + shorter.pop() + add;
            if (tmp_val < 10) {
                final_val.push(tmp_val);
                add = 0;
            } else {
                final_val.push(tmp_val - 10);
                add = 1;
            }
        }
        while (longer.size() > 0) {
            int tmp_val = longer.pop() + add;
            if (tmp_val < 10) {
                final_val.push(tmp_val);
                add = 0;
            } else {
                final_val.push(tmp_val - 10);
                add = 1;
            }
        }
        if (add != 0) final_val.push(add);
        return final_val;
    }


    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1.val == 0) return l2;
        if (l2.val == 0) return l1;

        List<Integer> val1 = new ArrayList<>();
        List<Integer> val2 = new ArrayList<>();

        while (l1 != null) {
            val1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            val2.add(l2.val);
            l2 = l2.next;
        }

        List<Integer> out = addList(val1, val2);
        ListNode res = new ListNode(out.get(out.size() - 1));
        ListNode mov = res;
        for (int i = out.size() - 2; i >= 0; i--) {
            mov.next = new ListNode(out.get(i));
            mov = mov.next;
        }

        return res;
    }

    private static List<Integer> addList(List<Integer> val1, List<Integer> val2) {
        List<Integer> out = new ArrayList<>();
        int i = val1.size() - 1;
        int j = val2.size() - 1;
        int addBit = 0;
        while (i >= 0 && j >= 0) {
            int a = val1.get(i);
            int b = val2.get(j);
            int sum = a + b + addBit;

            if (sum >= 10) {
                out.add(sum - 10);
                addBit = 1;
            } else {
                out.add(sum);
                addBit = 0;
            }
            i--;j--;
        }

        List<Integer> temp;
        if (j < 0) temp = addRest(val1, i, addBit);
        else temp = addRest(val2, j, addBit);

        out.addAll(temp);
        return out;
    }

    private static List<Integer> addRest(List<Integer> val, int idx, int addBit) {
        List<Integer> out = new ArrayList<>();
        for (int k = idx; k >= 0; k--) {
            int a = val.get(k);
            int sum = a + addBit;
            if (sum >= 10) {
                out.add(sum - 10);
                addBit = 1;
            } else {
                out.add(sum);
                addBit = 0;
            }
        }
        if (addBit != 0) out.add(1);
        return out;
    }

}
