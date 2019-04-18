# 数组中只出现一次的数字

## Description

一个整型数组里除了**两个**数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。

## Solution

```java
/**
* 两个相同的数字做异或操作的结果是0
* 从头到尾异或所有数字，得到的结果就是两个只出现了一次的数字的异或结果。
* 将原来的数组分割为两个数组，使得两个只出现了一次的数字被分别分在两个数组里
* 对两个数组做异或操作
*/
public void FindNumsAppearOnce(int[] array, int[] num1, int[] num2) {
  int ptr = 0; 
  //对数组中所有数字做一遍异或操作
  for (int i = 0; i < array.length; i++) {
    ptr ^= array[i];
  }
  int index = 0;
  //找出异或结果中最右位置的1，这代表我们要找的两个数在这一位上不同，我们可以根据这一位上的值将原数组分成两个子数组
  while ((ptr & 1) == 0) {
    ptr >>= 1;
    index++;
  }
  for (int i = 0; i < array.length; i++) {
    if (isBit(array[i], index)) num1[0] ^= array[i];
    else num2[0] ^= array[i];
  }
}
private boolean isBit(int num, int index) {
  num >>= index;
  return ((num & 1) == 0);
}
```

