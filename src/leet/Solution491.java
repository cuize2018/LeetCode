package leet;

import java.util.*;

public class Solution491 {
    public Set<List<Integer>> out = new HashSet<>();
    public Stack<Integer> one = new Stack<>();

    public static void main(String[] args) {
        int[] exp = {4,6,7,7};
        Solution491 sol = new Solution491();

        System.out.println(sol.findSubsequences(exp));
    }

    /**
     * 深度优先DFS
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums.length < 2)return new ArrayList<>(out);

        for (int i = 0;i<nums.length;i++){
            one.add(nums[i]);
            dfs(Arrays.copyOfRange(nums, i+1, nums.length));
            one.pop();
        }
        return new ArrayList<>(out);
    }

    public void dfs(int[] nums){
        if (nums.length == 0)return ;

        if (nums.length == 1){
            if (nums[0] >= one.peek()){
                one.push(nums[0]);
                out.add(new ArrayList<>(one));
                one.pop();
            }
            return;
        }

        for (int i = 0;i<nums.length;i++){
            if (nums[i] >= one.peek()){
                one.push(nums[i]);
                if (one.size() >= 2)out.add(new ArrayList<>(one));

                dfs(Arrays.copyOfRange(nums, i+1, nums.length));
                one.pop();
            }
        }
    }

}
