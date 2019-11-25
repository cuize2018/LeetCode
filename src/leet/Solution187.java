package leet;

import com.sun.corba.se.impl.orbutil.ObjectUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution187 {

    public static void main(String[] args){
        String s = "AAAAAAAAAAA";
        System.out.println(findRepeatedDnaSequences(s));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        List<String> out = new ArrayList<>();
        for (int i = 0;i<=s.length()-10;i++){
            String tmp = s.substring(i,i+10);
            if (!map.containsKey(tmp)){
                map.put(tmp, 1);
            }else {
                map.put(tmp, map.get(tmp)+1);
            }
        }

        for (String key : map.keySet()){
            if (map.get(key) > 1){
                out.add(key);
            }
        }
        return out;
    }
}
