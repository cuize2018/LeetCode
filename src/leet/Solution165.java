package leet;

public class Solution165 {
    public static void main(String[] args){
        String v1 = "1.0";
        String v2 = "1.0.0";
        System.out.println(compareVersion(v1, v2));
    }

    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int out = 0;
        for (int i = 0;i < Math.min(v1.length, v2.length);i++){
            int num_v1 = Integer.parseInt(v1[i]);
            int num_v2 = Integer.parseInt(v2[i]);

            if (num_v1 < num_v2){
                out = -1;
                return out;
            }
            else if (num_v1 > num_v2){
                out = 1;
                return out;
            }
        }

        if (v1.length > v2.length){
            for (int i = v2.length;i<v1.length;i++){
                if (Integer.parseInt(v1[i]) != 0){
                    out = 1;return out;
                }
            }
        }
        else if (v2.length > v1.length){
            for (int i = v1.length;i<v2.length;i++){
                if (Integer.parseInt(v2[i]) != 0){
                    out = -1;return out;
                }
            }
        }

        return out;
    }
}
