package leet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution474 {
    public static void main(String[] args) {
//        String[]b = {"10", "0001", "111001", "1", "0"};
//        int m = 5;
//        int n = 3;

//        String[] b = {"111", "1000", "1000"};
//        int m = 9;
//        int n = 3;

//        String[] b = {"10","0001","111001","1","0"};
//        int m = 4;
//        int n = 3;

//        String[] b = {"10","0","1"};
//        int m = 1;
//        int n = 1;

//        String[] b = {"1100","100000","011111"};
//        int m = 6;
//        int n = 6;

        String[] b = {"011111","001","001"};
        int m = 4;
        int n = 5;
        System.out.println(findMaxForm(b,m,n));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        List<String> str_list = new LinkedList<>(Arrays.asList(strs));
        int count = 0;

        int num_0 = 0;
        int num_1 = 0;

        int m_old = m;
        int n_old = n;

        while (!str_list.isEmpty()) {
            if (m > n) {
                str_list.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int num0 = 0;
                        int num1 = 0;
                        for (char c : o1.toCharArray()) {
                            if (c == '0') num0++;
                            else num1++;
                        }

                        int str2_num0 = 0;
                        int str2_num1 = 0;
                        for (char c : o2.toCharArray()) {
                            if (c == '0') str2_num0++;
                            else str2_num1++;
                        }

                        if (num1 < str2_num1)return -1;
                        else if (num1 > str2_num1)return 1;
                        else return Integer.compare(o1.length(), o2.length());
                    }
                });
            } else if (m <= n) {
                str_list.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int num0 = 0;
                        int num1 = 0;
                        for (char c : o1.toCharArray()) {
                            if (c == '0') num0++;
                            else num1++;
                        }

                        int str2_num0 = 0;
                        int str2_num1 = 0;
                        for (char c : o2.toCharArray()) {
                            if (c == '0') str2_num0++;
                            else str2_num1++;
                        }


                        if (num0 < str2_num0)return -1;
                        else if (num0 > str2_num0)return 1;
                        else return Integer.compare(o1.length(), o2.length());
                    }
                });
            }
//            else {
//                str_list.sort(new Comparator<String>() {
//                    @Override
//                    public int compare(String o1, String o2) {
//                        return Integer.compare(o1.length(), o2.length());
//                    }
//                });
//            }
            for (String tmp : str_list) {
                num_0 = 0;
                num_1 = 0;
                for (char c : tmp.toCharArray()) {
                    if (c == '0') num_0++;
                    else num_1++;
                }
                if (m >= num_0 && n >= num_1) {
                    m_old = m;
                    n_old = n;

                    m -= num_0;
                    n -= num_1;
                    count++;
                    str_list.remove(tmp);
                    break;
                }
            }

            if (m_old < num_0 || n_old < num_1) {
                break;
            }
        }

        return count;
    }

    /**
     * 这道题和经典的背包问题很类似，不同的是在背包问题中，我们只有一种容量，而在这道题中，我们有 0 和 1 两种容量。
     * 每个物品（字符串）需要分别占用 0 和 1 的若干容量，并且所有物品的价值均为 1。因此我们可以使用二维的动态规划。
     *
     * 我们用 dp(i, j) 表示使用 i 个 0 和 j 个 1，最多能拼出的字符串数目，那么状态转移方程为：
     *      dp(i, j) = max(1 + dp(i - cost_zero[k], j - cost_one[k]))
     *      if i >= cost_zero[k] and j >= cost_one[k]
     *
     * 其中 k 表示第 k 个字符串，cost_zero[k] 和 cost_one[k] 表示该字符串中 0 和 1 的个数。
     * 我们依次枚举这些字符串，并根据状态转移方程更新所有的 dp(i, j)。
     * 注意由于每个字符串只能使用一次（即有限背包），因此在更新 dp(i, j) 时，i 和 j 都需要从大到小进行枚举。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String str : strs){
            int[] info = cal_info(str);
            int nums_0 = info[0];
            int nums_1 = info[1];

            for (int i = m;i >= nums_0;i--){
                for (int j = n;j >= nums_1;j--){
                    dp[i][j] = Math.max(dp[i-nums_0][j-nums_1]+1, dp[i][j]);
                }
            }
        }
        return dp[m][n];
    }

    public static int[] cal_info(String str){
        int[] tmp = new int[2];
        for (char c : str.toCharArray()){
            if (c == '0'){
                tmp[0]++;
            }
            else tmp[1]++;
        }
        return tmp;
    }

}
