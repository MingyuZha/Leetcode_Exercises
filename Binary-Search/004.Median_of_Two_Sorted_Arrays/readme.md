# Leetcode 4. Median of Two Sorted Arrays

## Description

There are two sorted arrays **nums1** and **nums2** of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume **nums1** and **nums2** cannot be both empty.

**Example 1:**

```python
nums1 = [1, 3]
nums2 = [2]
The median is 2.0
```

**Example 2:**

```python
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5
```

## Solution 

这道题之所以为hard难度，是因为题目要求我们给出的解法的时间复杂度为**O(log(m+n))**。

要达到这样的复杂度，那么很容易就可以联想到解法应该和**二分查找**有关系，事实上也的确如此。

事实上，要找到两个数组整体的median，其实可以把问题看作将数组A和数组B合并以后，再分成两个等长的子集：

```python
      left_part          |        right_part
A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
```

我们需要确保以下两个条件：

```python
1. len(left_part)=len(right_part)
2. Max(left_part) <= Min(right_part)
```

进一步看，i和j之间并不是相互独立的，一旦确定下了i的值，那么为了满足上面两个条件，j的值也就确定下来了：

```java
half_len = (m+n+1)/2;
j = half_len - i; //n **must** > m, otherwise, j might be negative
```

接下来，我们就可以利用二分查找的思想来确定i的取值了，注意，i的取值范围为**[0, m]**

具体二分查找的实施步骤如下：

* 设置i = (imin+imax) / 2，j = (m+n+1)/2 - i
* 如果B[j-1] <= A[i]并且A[i-1] <= B[j]，那么说明我们找到了符合条件的i和j的取值，可以结束查找
* 如果B[j-1] > A[i]，说明A[i]**太小**了，设置imin = i+1
* 如果A[i-1] > B[j]，说明B[j]太小了，或者说，A[i-1]**太大**了，设置imax = i-1

核心代码：

```java
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
```



