# 剑指offer：不用加减乘除做加法

## Description

写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。

## Solution

```java
/**
	既然不允许使用+, -, *, /，那么就只能够使用位运算了
	
	该题的解法分为三步：
	
	* Step 1: 将两数相加不计进位，相加可以用异或操作来实现，在这一步里, 0+1=1, 0+0=0, 1+1=0
	* Step 2: 计算进位：可以将两数做位与运算，然后将计算的结果向左移动一位
	* Step 3: 重复1，2两步，直到不存在进位
*/
public class Solution {
    public int Add(int num1,int num2) {
        int sum = 0, carry = 0;
        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;
            num1 = sum; num2 = carry;
        } while (carry != 0);
        return sum;
    }
}
```

