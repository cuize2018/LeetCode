package leet;

import java.util.HashMap;
import java.util.Map;

public class Solution464 {
    public static void main(String[] args) {
        int max = 4;
        int total = 6;
        System.out.println(canIWin(max,total));
    }

    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int max_val = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if (desiredTotal > max_val)return false;
        if (desiredTotal <= maxChoosableInteger)return true;

        Map<Integer, Boolean> map = new HashMap<>();
        return canWin(maxChoosableInteger, desiredTotal, 0, map);
    }
    //由于maxChoosableInteger 不会大于 20，所以可以使用一个int型的各个位标记是否使用
    //Map[used]用于标记在使用used（二进制各个位真值代表某个元素是否已经使用，比如used = “1101”代表使用了1，3，4）情况本次挑选是否能赢
    private static boolean canWin(int length, int target, int used, Map<Integer, Boolean> map){
        if (map.containsKey(used)){
            //如果之前搜索过
            return map.get(used);
        }
        //穷举当前可选的元素
        for (int i = 0;i < length;i++){
            int curr = 1 << i;//第i位表示选择[1,2,3, maxChoosableInteger]选择i + 1这个值
            if ((curr & used) == 0){//这个值没有使用过
                //nowTarget <= i + 1是代表已经达到预期值
                //nowTarget - (i + 1)表示选择了i + 1
                //cur | used代表更新各个元素使用情况，使用i + 1，将used的第i位（从第到高）标记为1
                //!canWin(length, total - (i + 1), cur | used, Map)表示的是对方选择输了
                if (target <= i+1 || !canWin(length, target-(i+1), curr|used, map)){
                    map.put(used, true);
                    return true;
                }
            }
        }
        map.put(used, false);
        return false;
    }
}
