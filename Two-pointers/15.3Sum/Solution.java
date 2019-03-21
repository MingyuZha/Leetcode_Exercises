import java.util.*;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            if (nums[i] <= 0 && (i == 0 || nums[i] != nums[i-1])) {
                int left = i + 1, right = nums.length-1, sum = -nums[i];
                while (left < right) {
                    if (nums[left] + nums[right] == sum) {
                        ret.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while (left < right && nums[right] == nums[right-1]) right--;
                        left++; right--;
                    } else if (nums[left] + nums[right] < sum) left++;
                    else right--;
                }
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Solution test = new Solution();
        System.out.println(test.threeSum(nums));
    }
}