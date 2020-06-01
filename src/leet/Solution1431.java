package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution1431 {
    public static void main(String[] args) {

    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new ArrayList<>();
        int max = -1;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }

        for (int candy : candies) {
            res.add(candy + extraCandies >= max);
        }
        return res;
    }
}
