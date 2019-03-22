import java.util.*;
public class Solution {
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = Integer.MIN_VALUE;
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {-2,-1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}