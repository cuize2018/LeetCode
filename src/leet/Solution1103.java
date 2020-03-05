package leet;

import java.util.Arrays;

public class Solution1103 {
    public static void main(String[] args) {
        int candies = 10;
        int num_people = 3;
        int[] distributeCandies = distributeCandies(candies, num_people);
        System.out.println(Arrays.toString(distributeCandies));
    }

    public static int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];

        int curr_candies = 1;
        int left_candies = candies;
        int mov;

        while (left_candies > 0) {
            mov = 0;
            while (mov < ans.length) {
                if (curr_candies < left_candies) ans[mov] += curr_candies;
                else {
                    ans[mov] += left_candies;
                    left_candies -= curr_candies;
                    break;
                }

                left_candies -= curr_candies;
                mov++;
                curr_candies++;
            }
        }
        return ans;
    }
}
