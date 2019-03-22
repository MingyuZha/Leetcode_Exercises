import java.util.*;
class Solution {
    public static int maxArea(int[] height) {
        int left = 0, right = height.length-1;
        int max_area = Integer.MIN_VALUE;
        while (left < right) {
            max_area = Math.max(max_area, Math.min(height[left], height[right])*(right-left));
            if (height[left] < height[right]) left++;
            else right--;
        }
        return max_area;
    }
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
}