package leet;

import java.util.*;

public class Solution406 {
    public static void main(String[] args) {
        int[][] a = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] c = {{9,0},{7,0},{1,9},{3,0},{2,7},{5,3},{6,0},{3,4},{6,2},{5,2}};
       int[][] out = reconstructQueue2(a);
        for (int[] ints : out) {
            System.out.println(Arrays.toString(ints));
        }

    }

    public static int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        List<int[]> tmp = new ArrayList<>();
        Collections.addAll(tmp, people);

        int count = 1;
        while (count != 0) {
            count = 0;
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (tmp.get(j)[0] >= tmp.get(i)[0]) {
                        sum++;
                    }
                }

                if (sum > tmp.get(i)[1]) {
                    int steps = sum - tmp.get(i)[1];
                    Collections.swap(tmp, i, i - steps);
                    count++;
                } else if (sum < tmp.get(i)[1]) {
                    int steps = tmp.get(i)[1] - sum;
                    Collections.swap(tmp, i, i + steps);
                    count++;
                }
            }
        }

        for (int i = 0;i<people.length;i++){
            people[i] = tmp.get(i);
        }
        return people;
    }

    /**
     * 解题思路：先排序再插入
     * 1.排序规则：按照先H高度降序，K个数升序排序
     * 2.遍历排序后的数组，根据K插入到K的位置上
     *
     * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     *
     * @param people
     * @return
     **/
    public static int[][] reconstructQueue2(int[][] people) {
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]

        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }

        return list.toArray(new int[list.size()][2]);
    }


}
