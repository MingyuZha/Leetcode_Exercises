import java.util.*;
class Solution {
    public static int longestCommonSubsequence(char[] arr1, char[] arr2) {
        int m = arr1.length, n = arr2.length;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr1[i-1] == arr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        printLCS(arr1, dp, m, n);
        System.out.printf("\n");
        return dp[m][n];
    }
    public static void printLCS(char[] arr1, int[][] dp, int i, int j) {
        if (i == 0 || j == 0) return;
        if (dp[i][j] == dp[i-1][j-1]+1) {
            printLCS(arr1, dp, i-1, j-1);
            System.out.printf("%c",arr1[i-1]);
        } else if (dp[i][j] == dp[i-1][j]) {
            printLCS(arr1, dp, i-1, j);
        } else {
            printLCS(arr1, dp, i, j-1);
        }

    }
    public static void main(String[] args) {
        char[] arr1 = {'b','d','c','a','b','a'};
        char[] arr2 = {'a','b','c','b','d','a','b'};
        System.out.println(longestCommonSubsequence(arr1, arr2));
    }
}