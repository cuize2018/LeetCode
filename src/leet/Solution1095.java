package leet;


public class Solution1095 {


    public static void main(String[] args) {
        MountainArray mountainArray = new MountainArray();
        int target = 5;
        Solution1095 solution1095 = new Solution1095();
        int idx = solution1095.findInMountainArray2(target, mountainArray);
        System.out.println(idx);
    }

    public int findInMountainArray2(int target, MountainArray mountainArr) {
        int low = 0;
        int length = mountainArr.length();
        int high = length - 1;
        int middle = 0;

        while (low < high) {
            middle = (low + high) >>> 1;

            int middleVal = mountainArr.get(middle);
            int middleLastVal = mountainArr.get(middle - 1);
            int middleNextVal =  mountainArr.get(middle + 1);

            //寻找峰值
            if (middleVal > middleLastVal && middleVal > middleNextVal){
                break;
            }
            else if (middleVal > middleLastVal){
                low = middle+1;
            }
            else if (middleVal > middleNextVal){
                high = middle;
            }
        }

        int idx = binearySearch(0, middle, mountainArr, target);
        if (idx != -1)return idx;

        int idx2 = binearySearch2(middle, length-1, mountainArr, target);
        return idx2;

    }

    private int binearySearch(int low, int high, MountainArray mountainArr, int target) {
        while (low <= high) {
            int middle = (low + high) >>> 1;
            int middleVal = mountainArr.get(middle);
            if (middleVal == target) return middle;

            if (target < middleVal) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    private int binearySearch2(int low, int high, MountainArray mountainArr, int target) {
        while (low <= high) {
            int middle = (low + high) >>> 1;
            int middleVal = mountainArr.get(middle);
            if (middleVal == target) return middle;

            if (target > middleVal) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }
}
