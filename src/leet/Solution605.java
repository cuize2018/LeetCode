package leet;

public class Solution605 {
    public static void main(String[] args) {

    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 0) return n == 0;
        if (flowerbed.length == 1) {
            if (flowerbed[0] == 0 && n <= 1) return true;
            if (flowerbed[0] == 1 && n == 0) return true;
            return false;
        }

        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (i == 0) {
                if (flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                    count++;
                    flowerbed[i] = 1;
                }
            } else if (i == flowerbed.length - 1) {
                if (flowerbed[i] == 0 && flowerbed[i - 1] == 0) {
                    count++;
                    flowerbed[i] = 1;
                }
            } else {
                if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    count++;
                    flowerbed[i] = 1;
                }
            }
        }
        return n <= count;
    }
}
