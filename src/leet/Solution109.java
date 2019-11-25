package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution109 {
    public static void main(String[] args){
        ListNode l = new ListNode(-10);
        ListNode h = l;
        l.next = new ListNode(-3);l=l.next;
        l.next = new ListNode(0);l=l.next;
        l.next = new ListNode(5);l=l.next;
        l.next = new ListNode(9);l=l.next;

        TreeNode out = sortedListToBST(h);
        int ff = 0;
    }

    public static TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode mov = head;
        while (mov != null){
            list.add(mov.val);
            mov = mov.next;
        }

        if (list.isEmpty())return null;
        if (list.size() == 1)return new TreeNode(list.get(0));

        int mid = list.size()/2;
        TreeNode root = new TreeNode(list.get(mid));

        List<Integer> left = list.subList(0,mid);
        List<Integer> right = list.subList(mid+1, list.size());

        root.left = buildSub(left);
        root.right = buildSub(right);

        return root;
    }


    private static TreeNode buildSub(List<Integer> list){
        if (list.isEmpty())return null;
        if (list.size() == 1)return new TreeNode(list.get(0));

        int mid = list.size()/2;
        TreeNode root = new TreeNode(list.get(mid));

        List<Integer> left = list.subList(0,mid);
        List<Integer> right = list.subList(mid+1, list.size());

        root.left = buildSub(left);
        root.right = buildSub(right);

        return root;
    }


}
