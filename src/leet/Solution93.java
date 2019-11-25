package leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution93 {
    private Set<String> res = new HashSet<>();
    public static void main(String[] args){
        String s = "25525511135";
        String s1 = "010010";
        System.out.println(new Solution93().restoreIpAddresses(s1));
    }

    public  List<String> restoreIpAddresses(String s){
        if (s.length() >= 4) {
            backTrack(1, s, "", 0);
            backTrack(2, s, "", 0);
            backTrack(3, s, "", 0);
        }
        else {
            return new ArrayList<>();
        }
        return new ArrayList<>(res);
    }

    private void backTrack(int num, String rest, String now, int level){
        if (level == 4 && !rest.isEmpty()){
            return;
        }
        if (rest.isEmpty() && level == 4){
            res.add(now);
            return;
        }

        if (rest.length() >= num) {
            String temp;
            String next = rest.substring(0,num);
            int val = Integer.parseInt(next);
            if (val < 0 || val > 255){
                return;
            }

            if (val != 0 && next.charAt(0) == '0'){
                return;
            }
            if (val == 0 && next.length() > 1){
                return;
            }

            if (!now.isEmpty()) temp = now + "." + next;
            else temp = next;

            String rest_new = rest.substring(num);

            backTrack(1, rest_new, temp, level+1);
            backTrack(2, rest_new, temp, level+1);
            backTrack(3, rest_new, temp, level+1);
        }
    }
}
