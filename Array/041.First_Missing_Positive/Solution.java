class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == i+1) continue;
            while (nums[i] > 0 && nums[i] <= n && nums[i] != i+1) {
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }         
            
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i+1) return i+1;
        }
        return n;
    }
    public static void main(String[] argv) {
        int[] nums = {3,4,-1,1};
        Solution test = new Solution();
        System.out.println(test.firstMissingPositive(nums));
    }
}