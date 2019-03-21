# Leetcode 15. 3Sum (Medium)

## Description

Given an array `nums` of *n* integers, are there elements *a*, *b*, *c* in `nums`such that *a* + *b* + *c* = 0? Find all unique triplets in the array which gives the sum of zero.

**Note:**

The solution set must not contain duplicate triplets.

**Example:**

```java
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

## Solution

将数组先进行排序，然后遍历整个数组，视当前的元素为可能的答案中的第一个元素，在确定了第一个元素之后，使用双指针遍历第一个元素之后的数组，看后面的数组元素有没有可以满足和为**-nums[i]**的，这里的操作其实就是求解**2Sum**的操作。

值得注意的是，为了使得答案中不包含重复解，除了使用Set来存储现有的答案之外，我们可以借助数组已经是排好序的性质，当当前遍历的第一个元素和之前遍历的元素相同时，直接跳过，因为即使后面存在解，也肯定已经在前一次遍历中找到了。

核心代码：

```java
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
```

