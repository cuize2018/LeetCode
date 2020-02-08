package leet;

import java.util.Arrays;

public class Solution135 {
    public static void main(String[] args) {

    }

    /**
     * 规则定义： 设学生 AA 和学生 BB 左右相邻，AA 在 BB 左边；
     * 左规则： 当 ratings_B>ratings_A时，B 的糖比 A 的糖数量多。
     * 右规则： 当 ratings_A>ratings_B时，A 的糖比 B 的糖数量多。
     *
     * 先从左至右遍历学生成绩 ratings，按照以下规则给糖，并记录在 left 中：
     *
     * 先给所有学生 1 颗糖；
     * 若 ratings_i>ratings_i-1，则第 i 名学生糖比第 i - 1 名学生多 1 个。
     * 若 ratings_i<=ratings_i-1，则第 i 名学生糖数量不变。（交由从右向左遍历时处理。）
     * 经过此规则分配后，可以保证所有学生糖数量 满足左规则 。
     * 同理，在此规则下从右至左遍历学生成绩并记录在 right 中，可以保证所有学生糖数量 满足右规则 。
     *
     * 最终，取以上 2轮遍历 left 和 right 对应学生糖果数的 最大值 ，这样则 同时满足左规则和右规则 ，即得到每个同学的最少糖果数量。
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        int length = ratings.length;
        if (length == 0)return 0;

        int[] left = new int[length];
        int[] right = new int[length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        for (int i = 1;i<length;i++){
            if (ratings[i] > ratings[i-1]){
                left[i] = left[i-1] + 1;
            }
        }

        for (int j = length-2;j >= 0;j--){
            if (ratings[j] > ratings[j+1]){
                right[j] = right[j+1] + 1;
            }
        }

        int sum = 0;
        for (int i = 0;i<length;i++){
            sum += Math.max(left[i], right[i]);
        }

        return sum;
    }
}
