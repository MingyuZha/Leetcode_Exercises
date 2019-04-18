# 数字在排序数组中出现的次数

## Description

统计一个数字在排序数组中出现的次数。

## Solution

```java
/**
    改良版二分查找，使得二分查找的返回值为数组中第一个出现该值的位置和最后一个出现该值的位置
* 时间复杂度：O(logN)
* 空间复杂度：O(1)
*/
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
        int first_index = GetFirstIndex(array, k);
        int last_index = GetLastIndex(array, k);
        return first_index == -1 ? 0 : last_index-first_index+1;
    }
    private int GetFirstIndex(int[] arr, int k) {
        int low = 0, high = arr.length-1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (arr[mid] == k) {
                if (mid == 0 || arr[mid] != arr[mid-1]) return mid;
                else high = mid-1;
            } else if (arr[mid] > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
    private int GetLastIndex(int[] arr, int k) {
        int low = 0, high = arr.length-1;
        while (low <= high) {
            int mid = (low+high)/2;
            if (arr[mid] == k) {
                if (mid == arr.length-1 || arr[mid] != arr[mid+1]) return mid;
                else low = mid+1;
            } else if (arr[mid] > k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
```

