package leet;

public class Solution440 {
    public static void main(String[] args) {

    }

    public static int findKthNumber(int n, int k) {
        int curr = 1;//字典序最小的数为1
        int prefix = 1;//前缀从1开始

        while (curr < k){
            int temp = count(n, prefix);//计算出当前前缀下所有子节点数目

            if (curr + temp > k){//如果第k小的数在当前前缀下
                prefix *= 10;//进入下一层
                curr++;//一直遍历到第K个退出循环
            }
            else {//如果第k小的数不在当前前缀下

                prefix++;//进入下一个前缀
                curr += temp;//跳过当前前缀下的所有子节点
            }
        }
        return prefix;
    }

    /**
     * 计算当前前缀下的所有子节点数目
     * @param n
     * @param prefix
     * @return
     */
    private static int count(int n, int prefix) {
        long curr_prefix = prefix;
        long next_prefix = curr_prefix+1;//下一个前缀峰头

        int count = 0;

        while (curr_prefix <= n){
            count += Math.min(n+1, next_prefix) - curr_prefix;//下一前缀减去当前前缀

            // 如果说刚刚prefix是1，next是2，那么现在分别变成10和20
            // 1为前缀的子节点增加10个，十叉树增加一层, 变成了两层

            // 如果说现在prefix是10，next是20，那么现在分别变成100和200，
            // 1为前缀的子节点增加100个，十叉树又增加了一层，变成了三层

            curr_prefix *= 10;//进入下一个层
            next_prefix *= 10;//进入下一个层
        }
        return count;
    }
}
