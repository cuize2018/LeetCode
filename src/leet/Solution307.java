package leet;

public class Solution307 {
    public static void main(String[] args){

    }


}

class NumArray {
    private int[] all_num;
    private int[] sum;
    public NumArray(int[] nums) {
        this.all_num = nums;
        this.sum = new int[nums.length];
        initSum();
    }

    public void update(int i, int val) {
        int old = all_num[i];
        int gap = val-old;
        for (int m = i;m<all_num.length;m++){
            sum[m] = sum[m] + gap;
        }
        all_num[i] = val;
    }

    public int sumRange(int i, int j) {
        if (i==0){
            return sum[j];
        }
        else {
            return sum[j]-sum[i-1];
        }
    }

    public void initSum() {
        if (all_num.length == 0)return ;
        sum[0] = all_num[0];
        for (int m = 1;m<sum.length;m++){
            sum[m] = sum[m-1]+all_num[m];
        }
    }
}
