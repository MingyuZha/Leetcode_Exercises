# 把数组排成最小的数

## Description

输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

## Solution

```java
/**
    {3, 32, 321}
要想使得拼接后的数字值最小，其实可以应用到贪心的思想，在选择哪个数放在最前面时，只需要确保最高位上的数最小即可
因此，不难想到其实求解此题只需要将数组中的数按照一种特定的排序规则重新排序即可。
*/
public String PrintMinNumber(int [] numbers) {
  Integer[] nums = new Integer[numbers.length];
  for (int i = 0; i < numbers.length; i++) nums[i] = new Integer(numbers[i]);
  Arrays.sort(nums, new Comparator<Integer>() {
    public int compare(Integer n1, Integer n2) {
      String str1 = String.valueOf(n1);
      String str2 = String.valueOf(n2);
      for (int i = 0; i < Math.max(str1.length(), str2.length()); i++) {
        char c1 = str1.charAt(i%str1.length()), c2 = str2.charAt(i%str2.length());
        if (c1 != c2) {
          return c1 - c2;
        }
      }
      return 0;
    }
  });
  String ans = "";
  for (Integer num : nums) {
    ans += String.valueOf(num);
  } 
  return ans;
}
```

