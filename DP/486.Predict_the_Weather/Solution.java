import java.util.*;
class Solution {
    // private Integer[][] dp;
    // public boolean PredictTheWinner(int[] nums) {
    //     dp = new Integer[nums.length][nums.length];
    //     int ans = helper(nums, 0, nums.length-1);
    //     return ans >= 0;
    // }
    // private int helper(int[] nums, int begin, int end) {
    //     if (begin == end) {
    //         dp[begin][end] = nums[begin];
    //         return nums[begin];
    //     }
    //     int left = nums[begin] - helper(nums, begin+1, end);
    //     int right = nums[end] - helper(nums, begin, end-1);
    //     dp[begin][end] = Math.max(left, right);
    //     return dp[begin][end];
    // }
    public boolean PredictTheWinner2(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = nums.length-1; i >= 0; i--) {
            for (int j = i+1; j < nums.length; j++) {
                if (j == i + 1) dp[i][j] = Math.max(nums[i]-nums[j], nums[j]-nums[i]);
                else {
                    dp[i][j] = Math.max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]);
                }
            }
        }
        return dp[0][nums.length-1] >= 0;
    }
    public static void main(String[] args) {
        Solution test = new Solution();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();
        boolean ans = test.PredictTheWinner2(nums);
        System.out.println(ans);
    }
}