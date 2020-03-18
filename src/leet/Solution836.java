package leet;

public class Solution836 {
    public static void main(String[] args) {

    }

    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        //长和宽的坐标都分别相交
        boolean rowIsOk = Math.max(rec1[0], rec2[0]) < Math.min(rec1[2],rec2[2]);
        boolean colIsOk = Math.max(rec1[1], rec2[1]) < Math.min(rec1[3],rec2[3]);

        return rowIsOk&&colIsOk;
    }
}
