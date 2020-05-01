package leet.interview;

import java.util.PriorityQueue;

public class Solution41 {
    public static void main(String[] args) {

    }
}

class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    int count = 0;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        count++;
        maxHeap.add(num);
        minHeap.add(maxHeap.remove());

        if ((count & 1) != 0) maxHeap.add(minHeap.remove());
    }

    public double findMedian() {
        if ((count & 1) != 0) return (double) maxHeap.peek();

        return (maxHeap.peek() + minHeap.peek()) / 2D;
    }
}