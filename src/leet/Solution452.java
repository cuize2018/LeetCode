package leet;

import java.util.*;

public class Solution452 {
    public static void main(String[] args) {
        int[][] a = {{10,16}, {2,8}, {1,6}, {7,12}};
        int[][] b = {{1,2}, {3,4}, {5,6}, {7,8}};
        int[][] e = {{1,2}, {2,3}, {3,4}, {4,5}};
        int[][] c = {{2,3}, {2,3}};
        int[][] d = {{7,15},{6,14},{8,12},{3,4},{4,13},{6,14},{9,11},{6,12},{4,13}};
        System.out.println(findMinArrowShots(e));
    }

    /**
     * 让我们根据气球的结束坐标进行排序，然后一个个进行检查。第一个气球是标有 0 的绿色气球，它的结束坐标是 6。
     *
     * 其他的气球有两种情况：
     *     开始坐标小于 6，例如红色气球，它可以与 0 气球一起被一支箭引爆。
     *     开始坐标大于 6，例如黄色气球，它不可以与 0 气球被一支箭引爆，因此需要增加箭的数量。
     *
     * 这代表了我们可以跟踪气球的结束坐标，若下个气球开始坐标在当前气球的结束坐标前，则我们可以用一支箭一起引爆；
     * 若下个气球的开始坐标在当前气球的结束坐标后，则我们必须0增加箭的数量。并跟踪下个气球的结束坐标。
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots2(int[][] points) {
        if (points.length == 0) return 0;

        // sort by x_end
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int[] p : points) {
            xStart = p[0];
            xEnd = p[1];
            // if the current balloon starts after the end of another one,
            // one needs one more arrow
            if (firstEnd < xStart) {
                arrows++;
                firstEnd = xEnd;
            }
        }

        return arrows;
    }

    /**
     * 先进行转化，变为按照区间结束点进行从小到大排序，得到排序后数组。
     * 从数组的第二个元素开始遍历：
     * 若起始点小于等于上个区间的结束点，则说明重叠，共用一支箭即可；
     * 若起始点大于上个区间的结束点，则说明不重叠，需要另外一支箭来解决。
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0)return 0;

        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int res = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end){
                res++;
                end = points[i][1];
            }
        }
        return res;

    }

}
