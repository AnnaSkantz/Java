package com.thealgorithms.dynamicprogramming;
// Partition a set into two subsets such that the difference of subset sums is minimum

/*
Input:  arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11

Input:  arr[] = {36, 7, 46, 40}
Output: 23
Explanation:
Subset1 = {7, 46} ;  sum of Subset1 = 53
Subset2 = {36, 40} ; sum of Subset2  = 76
 */
public class MinimumSumPartition {
    private static boolean[][] dp;

    /**
     * Calculates the minimum sum partition
     * @param arr - An array containing the set of numbers we are going to partition.
     * @return - An int representing the minimum sum partition.
     */
    public static int subSet(int[] arr) {
        int n = arr.length;
        int sum = getSum(arr);

        initMatrix(n+1, sum+1);
        // fill dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] < j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else if (arr[i - 1] == j) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int[] index = fillIndexArray(sum);
        return getMin(index, sum);
    }

    /**
     * Initializes the matrix used for the dynamic programming.
     * @param i - The amount of columns in the matrix.
     * @param j - The amount of rows in the matrix.
     */
    private static void initMatrix(int cols, int rows) {
        dp = new boolean[cols][rows];
        for (int i = 0; i <= cols; i++) {
            dp[i][0] = true;
        }
        for (int j = 0; j <= rows; j++) {
            dp[0][j] = false;
        }
    }

    /**
     * Inits and fills the index matrix with the correct values.
     * @param - The length of the index array.
     * @return - Returns the filled index matrix.
     */
    private static int[] fillIndexArray(int length) {
        // fill the index array
        int[] index = new int[length];
        int p = 0;
        for (int i = 0; i <= length / 2; i++) {
            if (dp[dp.length-1][i]) {
                index[p++] = i;
            }
        }
        return index;
    }

    /**
     * Calculate sum of array elements
     *
     * @param arr the array
     * @return sum of given array
     */
    public static int getSum(int[] arr) {
        int sum = 0;
        for (int temp : arr) {
            sum += temp;
        }
        return sum;
    }

    public static int getMin(int[] arr, int sum) {
        if (arr.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int temp : arr) {
            min = Math.min(min, sum - 2 * temp);
        }
        return min;
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        assert subSet(new int[]{1, 6, 11, 5}) == 1;
        assert subSet(new int[]{36, 7, 46, 40}) == 23;
        assert subSet(new int[]{1, 2, 3, 9}) == 3;
    }
}
