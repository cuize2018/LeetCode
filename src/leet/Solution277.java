package leet;

public class Solution277 {
    int[][] graph = {
            {1, 1, 0},
            {0, 1, 0},
            {1, 1, 1}
    };

    public static void main(String[] args) {
        Solution277 solution277 = new Solution277();
        int celebrity = solution277.findCelebrity(3);
        System.out.println(celebrity);

    }

    public int findCelebrity(int n) {
        for (int j = 0; j < n; j++) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (!knows(i, j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                boolean flag2 = true;
                for (int k = 0; k < n; k++) {
                    if (j != k && knows(j, k)) {
                        flag2 = false;
                        break;
                    }
                }
                if (flag2) return j;
            }
        }
        return -1;
    }

    public int findCelebrity2(int n) {
        int famous = 0;
        // 先找到名人的候选下标
        for (int j = 1; j < n; j++) {
            if (knows(famous ,j)){
                famous = j;
            }
        }

        // 判断是否符合条件
        for (int j = 0; j < n; j++) {
            if (j == famous)continue;;
            if (knows(famous, j))return -1;
            if (!knows(j, famous))return -1;
        }
        return famous;
    }

    public int findCelebrity3(int n) {
        int left = 0;
        int right = n-1;
        // 先排除掉不是名人的候选人
        while (left != right){
            if (knows(left, right))left++;
            else right--;
        }

        // 最后left和right会相遇，这个人是潜在的名人
        // 按照定义验证：
        // case1：候选人认识其他人，那么他不是名人
        // case2：存在至少一个人不认识候选人，那么他也不是名人
        for (int i = 0; i < n; i++) {
            if (i == left)continue;;
            if (knows(left, i))return -1;
            if (!knows(i, left))return -1;
        }
        return left;

    }

    public boolean knows(int a, int b) {
        return graph[a][b] == 1;
    }
}
