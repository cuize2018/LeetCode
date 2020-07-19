package leet;

import java.util.HashSet;
import java.util.Set;

public class Solution142 {
    public static void main(String[] args){
//        ListNode head = new ListNode(3);ListNode head_c = head;
//        head.next = new ListNode(2);head = head.next;ListNode in = head;
//        head.next = new ListNode(0);head = head.next;
//        head.next = new ListNode(4);head = head.next;
//        head.next = in;

//        ListNode head = new ListNode(1);ListNode head_c = head;ListNode in = head;
//        head.next = new ListNode(2);head = head.next;
//        head.next = in;

        ListNode head = new ListNode(1);ListNode head_c = head;

        ListNode ins = detectCycle(head_c);
        int ff = 0;
    }

    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> NodeSet = new HashSet<>();
        ListNode mov = head;
        while (mov != null){
            if (!NodeSet.contains(mov)) {
                NodeSet.add(mov);
            }
            else {
                return mov;
            }
            mov=mov.next;
        }
        return null;
    }

    /**
     * 算法流程：
     * 双指针第一次相遇： 设两指针 fast，slow 指向链表头部 head，fast 每轮走 2 步，slow 每轮走 1 步；
     *
     * 第一种结果： fast 指针走过链表末端，说明链表无环，直接返回 null；
     *
     * TIPS: 若有环，两指针一定会相遇。因为每走 1 轮，fast 与 slow 的间距 +1，fast 终会追上 slow；
     * 第二种结果： 当fast == slow时， 两指针在环中 第一次相遇 。下面分析此时fast 与 slow走过的 步数关系 ：
     *
     * 设链表共有 a+b 个节点，其中 链表头部到链表入口 有 a个节点（不计链表入口节点）， 链表环 有 b 个节点（这里需要注意，a和 b 是未知数，例如图解上链表 a=4 , b=5）；设两指针分别走了 f，s 步，则有：
     * fast 走的步数是slow步数的 2 倍，即 f = 2s；（解析： fast 每轮走 2 步）
     * fast 比 slow多走了 n 个环的长度，即 f = s + nb；（解析： 双指针都走过 a 步，然后在环内绕圈直到重合，重合时 fast 比 slow 多走环的长度整数倍）；
     * 以上两式相减得：f = 2nb，s = nb，即fast和slow 指针分别走了 2n，n 个 环的周长 （注意： n 是未知数，不同链表的情况不同）。
     * 目前情况分析：
     *
     * 如果让指针从链表头部一直向前走并统计步数k，那么所有走到链表入口节点时的步数是：k=a+nb（先走 a 步到入口节点，之后每绕 1 圈环（ b 步）都会再次到入口节点）。
     * 而目前，slow 指针走过的步数为 nb 步。因此，我们只要想办法让 slow 再走 a 步停下来，就可以到环的入口。
     * 但是我们不知道 a 的值，该怎么办？依然是使用双指针法。我们构建一个指针，此指针需要有以下性质：此指针和slow 一起向前走 a 步后，两者在入口节点重合。
     * 那么从哪里走到入口节点需要 a 步？答案是链表头部head。
     * 双指针第二次相遇：
     *
     * slow指针位置不变 ，将fast指针重新指向链表头部节点；slow和fast同时每轮向前走1步；
     * TIPS：此时 f = 0，s = nb；
     * 当 fast 指针走到f = a 步时，slow 指针走到步s = a+nb，此时 两指针重合，并同时指向链表环入口 。
     * 返回slow指针指向的节点。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
     * @param head
     * @return
     */
    public static ListNode detectCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (true){
            if (fast == null || fast.next == null)return null;
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)break;
        }

        fast = head;

        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }


    public static ListNode detectCycle3(ListNode head) {
        if (head == null)return null;
        ListNode fast = head;
        ListNode slow = head;

        while (true){
            if (fast == null || fast.next == null)return null;
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)break;
        }

        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;

    }
}
