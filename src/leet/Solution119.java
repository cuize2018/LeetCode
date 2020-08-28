package leet;

import java.util.ArrayList;
import java.util.List;

public class Solution119 {
    public static void main(String[] args) {

    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> prev = new ArrayList<>();
        prev.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> temp = new ArrayList<>();

            temp.add(1);
            for (int j = 0; j < i - 1; j++) {
                temp.add(prev.get(j) + prev.get(j + 1));
            }
            temp.add(1);
            prev = temp;
        }
        return prev;
    }

}
