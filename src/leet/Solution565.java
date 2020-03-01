package leet;

import java.util.*;

public class Solution565 {
    public static void main(String[] args) {
        int[] nums = {5,4,0,3,1,6,2};
        System.out.println(arrayNesting2(nums));
    }

    public static int arrayNesting2(int[] nums) {
        Map<Integer, Set<Integer>> map = new HashMap();
        Set<Integer> set = new HashSet<>();
        int start = 0;
        int count = 0;

        int idx = start;
        while (idx <= nums.length-1) {
            while (!set.contains(nums[idx])) {
                if (map.containsKey(idx)) {
                    set.addAll(map.get(idx));
                    break;
                }
                set.add(nums[idx]);

                Set<Integer> tmp;
                if (map.containsKey(idx)){
                    tmp = map.get(idx);
                }
                else {
                    tmp = new HashSet<>();
                }
                tmp.add(nums[idx]);
                map.put(idx, tmp);

                idx = nums[idx];
            }
            count = Math.max(count, set.size());
            set.clear();
            start++;

            idx = start;
        }
        return count;
    }

    /**
     * 箭头所示的当前嵌套中的元素形成一个循环。因此，不管选择添加到这些标记元素的集合中的第一元素如何，相同的元素将被添加到当前集合。
     *
     * 因此，当我们向对应于任何索引的集合添加元素nums[j]时，我们将其位置标记为在 visited数组中为true。
     * 这样做是为了在将来选择此索引作为起始索引时，我们不会进行冗余 temp_count 计算，因为我们已经考虑了与此索引链接的元素。
     *
     * 通过这样做，我们确保不会一次又一次地考虑重复集。
     *
     * 此外，我们还可以观察到索引 i 和 j 中的两个元素都不会导致跳转到相同的索引 k，
     * 因为它需要 nums [i] = nums [j] = k，这是不可能的，因为所有元素都是不同的。
     *
     * @param nums
     * @return
     */
    public static int arrayNesting(int[] nums){
        boolean[] visited = new boolean[nums.length];
        int count = 0;
        for (int i = 0;i<nums.length;i++){
            if (!visited[i]){
                int idx = i;
                int temp_count = 0;
                do {
                    temp_count++;
                    visited[idx] = true;
                    idx = nums[idx];
                }while (idx != i);
                count = Math.max(count, temp_count);
            }
        }
        return count;
    }
}
