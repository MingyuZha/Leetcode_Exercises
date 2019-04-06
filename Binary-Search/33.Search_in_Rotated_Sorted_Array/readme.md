# Leetcode 33. Search in Rotated Sorted Array

## Description

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`).

You are given a target value to search. If found in the array return its index, otherwise return `-1`.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of *O*(log *n*).

**Example 1:**

```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

**Example 2:**

```
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

## Solution

#### Solution #1

```java
/**
    4 5 6 7 0 1 2
    4 5 6 7 | 0 1 2
如果我们能找到数组中最小的那个数，就可以把原数组拆解成两个数组，每个拆解后的数组都是单调递增的
假设我们要找的target = 0
通过判断target是否小于nums[left] 或 if (target <= nums[right])，我们就可以得知我们需要在哪一个数组中继续查找
*/
public int search(int[] nums, int target) {
  if (nums.length == 0) return -1;
  int left = 0, right = nums.length-1, ans = -1;
  /**
  找出数组中最小的数
  */
  while (left < right) {
    int mid = (left + right) / 2;
    if (nums[mid] > nums[right]) left = mid+1;
    else right = mid;
  }
  int root = left;
  
  left = 0; right = nums.length-1;
  /**
  如果目标数在右侧单调升序数组中，则在右侧数组中做二分查找
  否则，在左侧单调递增序列中做二分查找
  */
  if (target <= nums[right]) {
    left = root;
    while (left <= right) {
      int mid = (left+right)/2;
      if (target == nums[mid]) return mid;
      if (target > nums[mid]) left=mid+1;
      else right = mid-1;
    }
  } else {
    right = root-1;
    while (left <= right) {
      int mid = (left+right)/2;
      if (target == nums[mid]) return mid;
      if (target > nums[mid]) left=mid+1;
      else right = mid-1;
    }
  }
  return -1;
}
```

#### Solution #2

```java
/**
		3   4   5   6   7   0   1   2
		|           |               |
	left         mid            right
每次二分数组，得到的两段数组总有一段是满足单调递增条件的。判别的标准就是：
	* if (nums[mid] >= nums[left]): left~mid段数组是单调增的
	* else: mid~right段数组是单调增的
* 在单调增的数组中，我们就可以做常规的二分查找就行了
* 如果target不在我们定位到的单调增数组段中，下一次搜索就直接在另一段数组中进行即可
*/
public int search(int[] nums, int target) {
  if (nums == null || nums.length == 0) return -1;
  int left = 0, right = nums.length-1;
  while (left <= right) {
    int mid = (left+right)/2;
    if (nums[mid] == target) return mid;
    if (nums[mid] >= nums[left]) {
      if (nums[left] <= target && target <= nums[mid])
        right = mid-1;
      else left = mid+1;
    } else {
      if (nums[mid] <= target && target <= nums[right]) 
        left = mid+1;
      else right = mid-1;
    }
  }
  return -1;
}
```

