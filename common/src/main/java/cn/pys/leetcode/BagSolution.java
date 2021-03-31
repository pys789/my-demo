package cn.pys.leetcode;

/**
 * @Description
 * @Date 2021/3/31 17:49
 * @Created by pengys
 */
public class BagSolution {
    public static void main(String[] args) {

        int capacity = 10;
        int[] weight = new int[]{2, 2, 6, 5, 4};
        int[] value = new int[]{6, 3, 5, 4, 6};

        BagSolution bagSolution = new BagSolution();
        System.out.println(bagSolution.bag(capacity, weight, value));
        //System.out.println(bagSolution.bag2(capacity, weight, value));

    }


    /**
     * 使用滚动数组
     *
     * @param capacity
     * @param weight
     * @param value
     * @return
     */
    private int bag2(int capacity, int[] weight, int[] value) {
        // dp[i][j]=v 第i件物品放入容量为j的背包中，最大价值为 v
        int[][] dp = new int[2][capacity + 1];

        for (int i = 0; i <= weight.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    dp[i % 2][j] = 0;
                    continue;
                }
                if (j >= weight[i - 1]) {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j - weight[i - 1]] + value[i - 1], dp[(i - 1) % 2][j]);
                } else {
                    dp[i % 2][j] = dp[(i - 1) % 2][j];
                }
            }
        }
        return dp[weight.length % 2][capacity];
    }

    /**
     * 动态规划普通解法
     *
     * @param capacity
     * @param weight
     * @param value
     * @return
     */
    private int bag(int capacity, int[] weight, int[] value) {
        // dp[i][j]=v 第i件物品放入容量为j的背包中，最大价值为 v
        int[][] dp = new int[weight.length + 1][capacity + 1];

        for (int i = 0; i <= weight.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if (j >= weight[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j - weight[i - 1]] + value[i - 1], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[weight.length][capacity];
    }
}
