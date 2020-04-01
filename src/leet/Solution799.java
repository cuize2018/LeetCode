package leet;

public class Solution799 {
    public static void main(String[] args) {
        int poured = 6;
        int query_row = 2;
        int query_glass = 1;
        System.out.println(champagneTower(poured, query_row, query_glass));
    }

    public static double champagneTower(int poured, int query_row, int query_glass) {
        if (poured == 0)return 0;
        double[][] nums = new double[100][];
        for (int i = 0; i < 100; i++) {
            nums[i] = new double[i + 1];
        }


        nums[0][0] = 1;

        int k = 2;
        while (k <= poured) {
            nums[0][0]++;
            for (int i = 0; i < 99; i++) {
                boolean all_zero = true;
                for (int j = 0; j < nums[i].length; j++) {
                    if (i == query_row && j == query_glass && nums[i][j] >= 1) return 1;

                    if (nums[i][j] > 1) {
                        all_zero = false;
                        if (j == 0) {
                            nums[i + 1][j] += (nums[i][j] - 1) / 2;
                            nums[i + 1][j + 1] += (nums[i][j] - 1) / 2;
                        } else if (j == nums[i].length - 1) {
                            nums[i + 1][j] += (nums[i][j] - 1) / 2;
                            nums[i + 1][j + 1] += (nums[i][j] - 1) / 2;
                        } else {
                            nums[i + 1][j] += (nums[i][j] - 1) / 2;
                            nums[i + 1][j + 1] += (nums[i][j] - 1) / 2;
                        }
                        nums[i][j]=1;
                    }

                }
                if (all_zero)break;
            }
            k++;
        }
        return nums[query_row][query_glass];
    }

    /**
     * 总的来说，如果流入一个杯子的香槟数量为 X，且 X 大于 1，那么就会有 Q = (X - 1.0) / 2 数量的香槟流入下面的两个杯子中。
     * 我们可以从第一层的杯子开始，计算出第二层每个杯子中流入的香槟数量，再计算出第三层的数量，直到计算到第 query_row 层。
     * @param poured
     * @param query_row
     * @param query_glass
     * @return
     */
    public static double champagneTower2(int poured, int query_row, int query_glass) {
        if (poured == 0)return 0;
        double[][] nums = new double[102][102];
        nums[0][0] = poured;

        for (int i = 0; i <= query_row; i++) {
            for (int j = 0; j <= query_glass; j++) {
                if (nums[i][j] > 1) {
                    double q = (nums[i][j] - 1) / 2;
                    nums[i + 1][j] += q;
                    nums[i + 1][j + 1] += q;
                }
            }
        }

        return Math.min(1, nums[query_row][query_glass]);
    }
}
