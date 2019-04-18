# 数组中出现次数超过一半的数字

## Description

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

## Solution

```java
/**
* 解法1：基于Partition函数的时间复杂度为O(N)的解法
* 假如数组中有一个数字出现的次数超过了数组长度的一半，那么如果把这个数组排序，那么排序之后位于数组中间的数字一定就是那个出现次数超过数组长度一半的数字
* 受快速排序算法的启发。在随机快速排序算法中，我们现在数组中随机选择一个数字，然后调整数组中数字的顺序，使得比选中的数字小的数字都排在它的左边，比选中的数字大的数字都排在它的右边。
*/
public int MoreThanHalfNum(int[] arr) {
  int left = 0, right = arr.length-1, mid = arr.length/2;
  while (left <= right) {
    int index = Partition(arr, left, right);
    if (index == mid) break;
    else if (index < mid) left = index+1;
    else right = index-1;
  }
  if (checkMoreThanHalf(arr)) return arr[mid];
  return 0;
}
private boolean checkMoreThanHalf(int[] arr) {
  int counts = 0;
  for (int num : arr) {
    if (num == arr[arr.length/2]) counts++;
  }
  return counts > arr.length/2;
}
private int Partition(int[] arr, int begin, int end) {
  int i = begin;
  for (int j = begin; j < end; j++) {
    if (arr[j] <= arr[end]) {
      swap(arr, i, j);
      i++;
    }
  }
  swap(arr, i, end);
  return i;
}
private void swap(int[] arr, int i, int j) {
  int temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}
```

```java
/**
* 基于数组特点的时间复杂度为O(N)的算法
* 数组中有一个数字出现的次数超过数组长度的一半，也就是说它出现的次数比其他所有数字出现次数的和还要多
* 我们可以考虑在遍历数组的时候保存两个值：一个是数组中的一个数字；另一个是次数
* 当我们遍历到下一个数字的时候，如果下一个数字和我们之前保存的数字相同，则次数加1；如果下一个数字和我们之前保存的数字不同，则次数减1。如果次数为0，那么我们需要保存下一个数字，并把次数设为1
* 由于我们要找的数字出现的次数比其他所有数字出现的次数之和还要多，那么要找的数字肯定是最后一次把次数设为1时对应的数字
*/
public int MoreThanHalfNum(int[] arr) {
  int counts = 0, ans = -1;
  for (int num : arr) {
    if (counts == 0) { counts = 1; ans = num; }
    if (num == ans) counts++;
    else counts--;
  }
  if (checkMoreThanHalf(arr, ans)) return ans;
  return 0;
}
private boolean checkMoreThanHalf(int[] arr, int ans) {
  int counts = 0;
  for (int num : arr) {
    if (num == ans) counts++;
  }
  return counts > arr.length/2;
}
```

