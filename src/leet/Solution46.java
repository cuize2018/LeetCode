package leet;
/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution46 {
    public static void main(String[] args){
        int[] nums = {1,2,3,4};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        Stack<Integer> list = new Stack<>();
        for (int i = 0;i<nums.length;i++){
            int[] r = new int[nums.length-1];
            int k = 0;
            for (int j = 0;j<nums.length;j++){
                if (j != i){
                    r[k] = nums[j];
                    k++;
                }
            }

            list.push(nums[i]);
            onePart(r, list, out);
            list.clear();
        }
        return out;
    }

    private static  void onePart(int[] rest, Stack<Integer> list, List<List<Integer>> out){
        if (rest.length == 0){
            out.add(new ArrayList<>(list));
        }
        for (int i = 0;i<rest.length;i++){
            int[] r = new int[rest.length-1];
            int k = 0;
            for (int j = 0;j<rest.length;j++){
                if (j != i){
                    r[k] = rest[j];
                    k++;
                }
            }

            list.push(rest[i]);
            onePart(r, list, out);
            list.pop();
        }
    }
}
