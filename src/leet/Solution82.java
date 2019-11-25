package leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution82 {
    public static void main(String[] args){
        ListNode l = new ListNode(1);
        ListNode h = l;
        l.next = new ListNode(1);l=l.next;
        l.next = new ListNode(3);l=l.next;
//        l.next = new ListNode(3);l=l.next;
//        l.next = new ListNode(4);l=l.next;
//        l.next = new ListNode(4);l=l.next;
//        l.next = new ListNode(5);l=l.next;
        //l.next = new ListNode(5);l=l.next;

//        l.next = new ListNode(2);l=l.next;
//        l.next = new ListNode(3);l=l.next;
//        l.next = new ListNode(3);l=l.next;
//        l.next = new ListNode(3);l=l.next;


        ListNode out = deleteDuplicates2(h);
        int v = 0;

    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode mov = head;
        Map<Integer,Integer> set = new HashMap<>();

        while (mov != null){
            if (!set.containsKey(mov.val)){
                set.put(mov.val, 1);
            }
            else {
                set.put(mov.val, set.get(mov.val)+1);
            }
            mov = mov.next;
        }

        mov = head;
        ListNode last = head;
        ListNode out = null;
        boolean ifFirst = true;
        while (mov != null){
            if (set.get(mov.val) > 1){
                mov = mov.next;
            }
            else {
                if (ifFirst) {
                    last = new ListNode(mov.val);
                    last.next = null;

                    out = last;
                    ifFirst = false;
                }
                else {
                    last.next = new ListNode(mov.val);
                    last = last.next;
                }
                mov = mov.next;
            }
        }
        return out;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode mov = head.next;
        ListNode last = head;
        int last_num = head.val;
        int last_num_count = 1;

        ListNode out = null;
        ListNode out_head = out;
        boolean ifFirst = true;

        while (mov != null){
            if (mov.val == last_num){
                last_num_count++;
                mov = mov.next;
            }
            else {
                if (ifFirst){
                    if (last_num_count == 1){
                        ifFirst = false;
                        out = last;
                        out_head = out;
                    }
                    last = mov;
                    last_num = mov.val;
                    last_num_count = 1;
                    mov = mov.next;
                }
                else {
                    if (last_num_count == 1){
                        out.next = last;
                        out = out.next;
                    }
                    last = mov;
                    last_num = mov.val;
                    last_num_count = 1;
                    mov = mov.next;
                }
            }
        }

        if (last_num_count == 1){
            if (out != null) {
                out.next = last;
            }
            else {
                out_head = last;
            }
        }
        else {
            if (out!=null) {
                out.next = null;
            }
        }
        return out_head;
    }
}
