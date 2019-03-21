import java.util.*;
class Solution {
    public int jump(int[] nums) {
        int cur_max_index = nums[0];
        int max_index = nums[0];
        int steps = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > cur_max_index) {
                cur_max_index = max_index;
                steps++;
            }
            max_index = Math.max(max_index, nums[i]+i);
        }
        return steps;
    }
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        Solution test = new Solution();
        System.out.println(test.jump(nums));
    }
}