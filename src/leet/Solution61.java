package leet;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 */
public class Solution61 {
    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        ListNode l1_copy = l1;
        l1.next = new ListNode(2);l1=l1.next;
        l1.next = new ListNode(3);l1=l1.next;
//        l1.next = new ListNode(4);l1=l1.next;
//        l1.next = new ListNode(5);l1=l1.next;

        ListNode out = rotateRight(l1_copy, 20000000);
        int c = 0;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null){
            return null;
        }
        ListNode head_copy = head;
        ListNode mov_t = head;
        int len = 0;
        while (mov_t!=null){
            len++;
            mov_t=mov_t.next;
        }
        int step = k % len;

        for (int i = 0; i < step;i++) {
            ListNode mov = head_copy;
            int pre_val = mov.val;
            mov = mov.next;

            while (mov != null) {
                int tmp_this_val = mov.val;
                mov.val = pre_val;
                mov = mov.next;
                pre_val = tmp_this_val;
            }
            head_copy.val = pre_val;
        }
        return head_copy;
    }
}
