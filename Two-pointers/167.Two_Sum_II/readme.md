# Leetcode 167. Two Sum II - Input array is sorted (和为s的两个数字）

## Description

**Leetcode描述**

Given an array of integers that is already **sorted in ascending order**, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

**Note:**

- Your returned answers (both index1 and index2) are not zero-based.
- You may assume that each input would have *exactly* one solution and you may not use the *same* element twice.

**Example:**

```
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
```

**剑指offer描述**

输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。

## Solution

```java
/**
	由于数组是排好序的，那么假如我们现在从数组中取出两个数，发现它们的和是小于target的，那么要想达到target，我们不可能在固定较大数的前提下，再去搜索比较小数还要小的数了
	
	因此，本题的思路是用两个指针ptr1, ptr2
	
	ptr1从数组的最左边开始，ptr2从数组的最右边开始扫描，如果两数之和大于target，则将ptr2向左移；如果两数之和小于target，则将ptr1向右移
	
	时间复杂度：O(N)
	空间复杂度：O(1)
*/
public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        int min_mul = Integer.MAX_VALUE;
        int ptr1 = 0, ptr2 = array.length-1;
        ArrayList<Integer> ans = new ArrayList<>();
        int index1 = -1, index2 = -1;
        while (ptr1 < ptr2) {
            if (array[ptr1] + array[ptr2] < sum) {
                ptr1++;
            } else if (array[ptr1] + array[ptr2] > sum) {
                ptr2--;
            } else {
                if (array[ptr1] * array[ptr2] < min_mul) {
                    min_mul = array[ptr1] * array[ptr2];
                    index1 = ptr1; index2 = ptr2;
                }
                ptr1++; ptr2--;
            }
        }
        if (index1 == -1) return ans;
        ans.add(array[index1]); ans.add(array[index2]);
        return ans;
    }
}
```

