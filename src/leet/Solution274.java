package leet;

import java.util.Arrays;

public class Solution274 {
    public static void main(String[] args){
        int[] a = {0};
        System.out.println(hIndex(a));
    }

    /**
     * 先把数组排序
     * 因为h指数含义为：
     *一名科研人员的 h 指数是指他（她）的 （N 篇论文中）"至多"有 h 篇论文分别被引用了"至少" h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次.)
     * 所以首先取h = min(window,  cit[len-window]),即先假设有x = window篇, 其次再选取这x=window篇论文引用数的最小值即 cit[len-window]
     * 取window和 cit[len-window]的最小值, 即为当前window时的h
     * 然后让window递减，计算出每一个window时的h，直到window为0时结束
     * 取每个window里的最大h为结果
     * exp:
     * [0,1,3,5,6]
     * x = window = 5 -> h = min(5,0) = 0
     * x = window = 4 -> h = min(4,1) = 1
     * x = window = 3 -> h = min(3,3) = 3
     * x = window = 2 -> h = min(2,5) = 2
     * x = window = 1 -> h = min(1,6) = 1
     *
     * 所以, h = 3
     *
     * @param citations
     * @return
     */
    /**
     * 可以按照如下方法确定某人的H指数：
     * 将其发表的所有SCI论文按被引次数从高到低排序；
     * 从前往后查找排序后的列表，直到某篇论文的序号大于该论文被引次数。所得序号减一即为H指数。
     * @param citations
     * @return
     */

    public static int hIndex(int[] citations) {
        if (citations.length == 0) return 0;

        Arrays.sort(citations);
        int window = citations.length;
        int h = Integer.MIN_VALUE;
        int win_tmp = window;

        while (win_tmp > 0){
            int h_tmp = Math.min(win_tmp, citations[window-win_tmp]);
            h = Math.max(h,h_tmp);
            win_tmp--;
        }

        return h;
    }
}
