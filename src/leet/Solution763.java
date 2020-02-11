package leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution763 {
    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(S));
    }

    public static List<Integer> partitionLabels(String S) {
        int index = 0;
        List<Integer> out = new ArrayList<>();

        while (index < S.length()){
            char c = S.charAt(index);
            int last_index = S.lastIndexOf(c);

            //找到最大的含有重复字母的下标
            for (int i = index+1;i<last_index;i++){
                char tmp = S.charAt(i);
                int last_index_tmp = S.lastIndexOf(tmp);
                last_index = Math.max(last_index, last_index_tmp);
            }

            out.add(last_index-index+1);
            index = last_index + 1;
        }

        return out;
    }
}
