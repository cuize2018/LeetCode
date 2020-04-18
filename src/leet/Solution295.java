package leet;

import java.util.*;

public class Solution295 {

    public static void main(String[] args) {
        MedianFinder2 medianFinder = new MedianFinder2();
        medianFinder.addNum(6);
        medianFinder.addNum(10);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
    }


}

/**
 * 1、数据是如何在两个堆之间流动的，脑子里要建立如下动态的过程：为了找到添加新数据以后，数据流的中位数，我们让这个新数据在大顶堆和小顶堆中都走了一遍。
 * 而为了让大顶堆的元素多 1 个，我们让从小顶堆中又拿出一个元素“送回”给大顶堆；
 *
 * 2、将元素放入优先队列以后，优先队列会以对数时间复杂度自行调整，把“最优值”放入堆顶，这是使用优先队列解决这个问题的原因。
 * 如果不太熟悉优先队列的朋友们，请复习一下优先队列的相关知识，包括基本操作，理解上浮和下沉。
 *
 */
class MedianFinder {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    int count;//当前大顶堆和小顶堆的元素个数之和
    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((x,y) -> Integer.compare(y,x));
        minHeap = new PriorityQueue<>();
        count = 0;
    }

    public void addNum(int num) {
        count++;
        maxHeap.add(num);
        minHeap.add(maxHeap.remove());
        // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
        if (count % 2 != 0){
            maxHeap.add(minHeap.remove());
        }
    }

    public double findMedian() {
        if (count % 2 == 0){
            // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
            return (maxHeap.peek() + minHeap.peek())/2D;
        }
        else {
            // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
            return (double)maxHeap.peek();
        }

    }
}


class MedianFinder2 {
    LinkedList<Integer> nums;
    /** initialize your data structure here. */
    public MedianFinder2() {
        nums = new LinkedList<>();
    }

    public void addNum(int num) {
        if (nums.isEmpty() || num >= nums.get(nums.size()-1)) {
            nums.add(num);
        }
        else {
            for (int i = 0; i < nums.size(); i++) {
                if (num < nums.get(i)){
                    nums.add(i, num);
                    break;
                }
            }
        }
    }

    public double findMedian() {
        int middle;
        if (nums.size() % 2 == 0){
            int a = nums.size() >>>1;
            int b = a - 1;
            return (nums.get(a)+nums.get(b))/2D;
        }
        else {
            middle = nums.size() >>> 1;
            return (double)nums.get(middle);
        }
    }
}
