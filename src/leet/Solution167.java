package leet;

public class Solution167 {
    public static void main(String[] args) {

    }

    public static int[] twoSum(int[] numbers, int target) {
        if (numbers.length == 0) return null;

        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) return new int[]{i + 1, j + 1};

            if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }
}
