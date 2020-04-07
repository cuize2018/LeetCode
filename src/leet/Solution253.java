package leet;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution253 {
    public static void main(String[] args) {
        int[][] intervals = {{0, 30},{5, 10},{15, 20}};
        System.out.println(minMeetingRooms(intervals));
    }

    /**
     * 1.按照开始时间对会议进行排序。
     * 2.初始化一个新的最小堆，将第一个会议的结束时间加入到堆中。我们只需要记录会议的结束时间，告诉我们什么时候房间会空。
     * 3.对每个会议，检查堆的最小元素（即堆顶部的房间）是否空闲。
     *     1.若房间空闲，则从堆顶拿出该元素，将其改为我们处理的会议的结束时间，加回到堆中。
     *     2.若房间不空闲。开新房间，并加入到堆中。
     * 4.处理完所有会议后，堆的大小即为开的房间数量。这就是容纳这些会议需要的最小房间数。
     *
     * 时间复杂度 ：O(NlogN)。
     * 时间开销主要有两部分。第一部分是数组的 排序 过程，消耗 O(NlogN) 的时间。
     * 接下来是 最小堆 占用的时间。在最坏的情况下，全部 N 个会议都会互相冲突。
     * 在任何情况下，我们都要向堆执行 N 次插入操作。
     * 在最坏的情况下，我们要对堆进行 N 次查找并删除最小值操作。
     * 总的时间复杂度为 O(NlogN)，因为查找并删除最小值操作只消耗 O(log N)的时间。
     *
     * @param intervals
     * @return
     */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0)return 0;

        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length;i++) {
            if (!heap.isEmpty() && heap.peek() <= intervals[i][0]){
                heap.remove();
            }
            heap.add(intervals[i][1]);
        }
        return heap.size();
    }
}
