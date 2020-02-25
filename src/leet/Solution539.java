package leet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution539 {
    public static void main(String[] args) {
//        String[] times = {"23:59","00:00"};
//        String[] times = {"12:12","12:13"};
        String[] times = {"01:39", "10:26", "21:43"};
        System.out.println(findMinDifference(Arrays.asList(times)));
    }

    /**
     * 先排序，两两比较后再比较首尾
     *
     * @param timePoints
     * @return
     */
    public static int findMinDifference(List<String> timePoints) {
        //由于分钟数最多为24*60，若时间点个数超过24*60，则必有重复，最小时间差为0.
        if (timePoints.size() > 1440) {
            return 0;
        }

        Collections.sort(timePoints);
        double min = Integer.MAX_VALUE;

        for (int i = 1; i < timePoints.size(); i++) {
            min = Math.min(min, comp(timePoints.get(i), timePoints.get(i - 1)));
            if (min == 0)
                return 0;
        }
        min = Math.min(min, comp(timePoints.get(0), timePoints.get(timePoints.size() - 1)));
        return ((int) Math.ceil(min * 600)) / 10;
    }


    public static double comp(String o1, String o2) {
        String[] time1 = o1.split(":");
        String[] time2 = o2.split(":");

        int h1 = Integer.parseInt(time1[0]);
        int h2 = Integer.parseInt(time2[0]);

        int min1 = Integer.parseInt(time1[1]);
        int min2 = Integer.parseInt(time2[1]);

        if ((h1 > 12 && h2 == 0)) {
            h2 = 24;
        }

        if ((h2 > 12 && h1 == 0)) {
            h1 = 24;
        }

        double val1 = (double) h1 + ((double) min1 / (double) 60);
        double val2 = (double) h2 + ((double) min2 / (double) 60);

        double temp = Integer.MAX_VALUE;
        if (h1 > 12 && h2 < 12) {
            temp = (24.0 - val1) + (val2 - 0.0);
        }
        if (h2 > 12 && h1 < 12) {
            temp = (24.0 - val2) + (val1 - 0.0);
        }

        return Math.min(Math.abs(val1 - val2), temp);
    }

    /**
     * 先转化为分钟在排序比较
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference2(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        int[] minutes = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String timeStr = timePoints.get(i);
            minutes[i] = Integer.parseInt(timeStr.substring(0, 2)) * 60 + Integer.parseInt(timeStr.substring(3));
        }
        Arrays.sort(minutes, 0, minutes.length);

        int mindiff = 1440;
        for (int i = 1; i < minutes.length; i++) {
            mindiff = Math.min(mindiff, minutes[i] - minutes[i - 1]);
            if (mindiff == 0)
                return 0;
        }
        mindiff = Math.min(1440 - minutes[minutes.length - 1] + minutes[0], mindiff);
        return mindiff;
    }
}
