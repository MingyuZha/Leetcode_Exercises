# Leetcode 287. Find the Duplicate Number (剑指offer: 数组中重复的数字)

## Description

在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。

## Solution

```java
/**
* 解法一：使用哈希表存储出现过的数字，空间换时间
	时间复杂度：O(N)
	空间复杂度：O(N)
	
* 解法二：空间复杂度为O(1)的解法
我们注意到数组中的数字都在0~n-1范围内，如果这个数组中没有重复的数字，那么当数组排序之后数字i将出现在下标为i的位置。由于数组中有重复的数组，有些位置可能存在多个数字，同时有些位置可能没有数字。

现在我们从头到尾一次扫描这个数组中的每个数字。当扫描到下标为i的数字时，首先比较这个数字(m)是否等于i
	a. 如果是，则接着扫描下一个数字
	b. 如果不是，则再拿它和下标为m的数字进行比较，如果它和那个数字相等，就找到了重复的数字；如果不相等，就把第i个数和第m个数字交换，把m放到属于它的位置。
	
*/
public class Solution {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null) return false;
        for (int i = 0; i < length; i++) {
            // 尽管这里有个内层循环，但是由于每个数字至多只需要交换两次就能到达属于自己的位置上，因此总体的时间复杂度为O(N)
            while (numbers[i] != i) {
                if (numbers[numbers[i]] == numbers[i]) {
                    duplication[0] = numbers[i];
                    return true;
                } else {
                    int temp = numbers[numbers[i]];
                    numbers[numbers[i]] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        return false;
    }
}
```

