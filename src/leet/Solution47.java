package leet;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class Solution47 {
    public static void main(String[] args){
        int[] a = {1,1,2};
        System.out.println(permuteUnique(a));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        Stack<Integer> list = new Stack<>();
        Set<Integer> set = new HashSet<>();

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
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                onePart(r, list, out);
            }
            list.clear();
        }
        return out;
    }

    private static  void onePart(int[] rest, Stack<Integer> list, List<List<Integer>> out){
        if (rest.length == 0){
            List<Integer> t = new ArrayList<>(list);
            out.add(t);
        }
        Set<Integer> set = new HashSet<>();//针对同一层次的计算，对连续的相同的元素只选取一个进行后续的替换，即可等价于基础全排列。
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
            if (!set.contains(rest[i])) {
                set.add(rest[i]);
                onePart(r, list, out);
            }
            list.pop();
        }
    }
}
