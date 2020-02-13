package leet;

public class Solution486 {

    public static void main(String[] args) {
        int[] nums = {1,5,2};

        Solution486 sol = new Solution486();
        System.out.println(sol.PredictTheWinner(nums));
    }

    public boolean PredictTheWinner(int[] nums) {

        return helper(nums,0,nums.length-1,0,0,true);
    }
    //分别为   nums数组, 左边可取的位置，右边可取的位置，A的得分,B的得分,轮到谁选牌
    public boolean helper(int[] nums, int left, int right, int player1, int player2, boolean isPlayer1){
        if (left > right){//左右越界，没有牌了，比较得分，判断胜负（以A为主角）
            return player1 >= player2;
        }

        if (isPlayer1){//轮到A选牌,A是主角，只要左边或者右边有一种必胜情况，就说明可以必胜
            return helper(nums, left+1,right,player1+nums[left],player2,false) ||
                    helper(nums, left, right-1, player1+nums[right], player2, false);
        }
        else { //轮到B选牌，不管B怎么选，此时只有左右两边都保证A是必胜的，才能保证A最终必胜！
            return helper(nums, left+1,right,player1,player2+nums[left],true) &&
                    helper(nums, left, right-1, player1, player2+nums[right], true);
        }
    }

}
