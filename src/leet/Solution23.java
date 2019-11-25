package leet;
import leet.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class Solution23 {
    public static void main(String[] args){

    }

    /**
     * 两两合并
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode out;
        if (lists.length == 0)return null;
        if (lists.length == 1)return lists[0];

        out = mergeTwoLists(lists[0], lists[1]);
        for (int i = 2;i<lists.length;i++){
            out = mergeTwoLists(out, lists[i]);
        }
        return out;
    }

    /**
     * 分治法
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        ListNode out;
        if (lists.length == 0)return null;
        if (lists.length == 1)return lists[0];

        List<ListNode> tmp = new ArrayList<>();
        List<ListNode> tmp2 = new ArrayList<>();
        Collections.addAll(tmp, lists);

        while (tmp.size() > 1){
            if (tmp.size()%2 == 0) {
                for (int i = 0; i < tmp.size(); i += 2) {
                    tmp2.add(mergeTwoLists(tmp.get(i), tmp.get(i+1)));
                }
            }
            else {
                for (int i = 0; i < tmp.size()-1; i += 2) {
                    tmp2.add(mergeTwoLists(tmp.get(i), tmp.get(i+1)));
                }
                tmp2.add(tmp.get(tmp.size()-1));
            }
            tmp.clear();
            tmp.addAll(tmp2);
            tmp2.clear();
        }
        out = tmp.get(0);
        return out;
    }

     private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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


