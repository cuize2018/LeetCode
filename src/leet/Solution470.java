package leet;

import java.util.Random;

public class Solution470 {
    public static void main(String[] args) {
        System.out.println(rand10());
    }

    /**
     * 第一步，把1到10分成1到5和6到10，用rand()若得1,2,3则为1到5，若得5,6,7则为6到10，若得4则循环直到选好为止。
     * 第二步：若选到1到5，用rand()若得1到5直接return,若得6,7则继续循环；若选到6到10与1到5思路一样，加5即可
     * @return
     */
    public static int rand10() {
        int num = rand7();
        while (num == 4){
            num = rand7();
        }

        int num2 = rand7();
        while (num2 >= 6){
            num2 = rand7();
        }

        if (num < 4){
            // 1 ~ 5
            return num2;
        }
        else {
            // 6 ~ 10
            return num2 + 5;
        }
    }

    public static int rand7(){
        Random random = new Random();
        return random.nextInt(7)+1;
    }

    public static int rand102(){
        int row = rand7();
        int col = rand7();
        int idx = (row-1)*7 + col;

        while (idx > 40){
            row = rand7();
            col = rand7();
            idx = (row-1)*7 + col;
        }
        return idx%10 == 0?10:idx%10;
    }


}
