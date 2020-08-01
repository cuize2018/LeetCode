package leet;

import java.util.List;
import java.util.PriorityQueue;

public class Solution632 {
    public static void main(String[] args) {

    }

    /**
     * 最小区间满足下面两点：
     * ·长度最小(首要)
     * ·长度相同时起点最小
     * 起点最小的区间我们是可以知道的。就是从每个区间中找最小的元素，组成的新的区间，我们称其为起始区间。
     * 如果之后没有长度比起始区间长度更短的，那么起始区间就是我们所求的最小区间，因为起始区间的起点是最小的。
     *
     * 那么我们所需要做的就是搜索是否有比起始区间还要短的区间了。
     * 答案是每次都将当前区间中最小的元素丢弃，换成其原始数组中的下一个元素。
     * 就是说，如果当前我们从每个区间中选取的元素分别是a1_1,a2_1,a3_1...ak_1(前面的数字代表来自第几个数组，后面的数字表示该数是该数组的第几个元素),
     * 若此时最小的元素是ai_1,最大元素是aj_1,那么区间就为[ai_1, aj_1],区间中最小元素是ai_1,那么我们就将ai_1丢弃,将ai_2拿出来放进去。
     * @param nums
     * @return
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        PriorityQueue<Node> heap = new PriorityQueue<>((x,y) -> Integer.compare(x.val, y.val));

        int end = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            heap.offer(new Node(nums.get(i).get(0), i));
            end = Math.max(end, nums.get(i).get(0));
        }

        int[] index = new int[n];
        int max = end;
        int start = heap.peek().val;
        int min = start;
        int len = max - min + 1;

        while (true){
            int group = heap.poll().group;
            if (index[group] == nums.get(group).size() - 1){
                break;
            }
            index[group]++;

            Node temp = new Node(nums.get(group).get(index[group]), group);
            heap.offer(temp);

            if (temp.val > max){
                max = temp.val;
            }
            min = heap.peek().val;

            if (max - min + 1 < len){
                start = min;
                end = max;
                len = end-start+1;
            }
        }
        return new int[]{start, end};
    }


    static class Node{
        int val;
        int group;

        public Node(int v, int g){
            val = v;
            group = g;
        }
    }
}
