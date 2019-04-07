# Leetcode 49. Group Anagrams

## Description

Given an array of strings, group anagrams together.

**Example:**

```
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

## Solution

```java
/**
   统计每个string包含的字符个数，并以此作为Hashmap的key
   例如：
       aat ata
       aat: [2,0,0,...,1,...,0] -> #2#0#0...#1#0...
       ata: [2,0,0,...,1,...,0] -> #2#0#0...#1#0...
* Time complexity: O(NK): N表示string的总个数，K表示string的最大长度
* Space comlexity: O(NK)
*/
public List<List<String>> groupAnagrams(String[] strs) {
  HashMap<String, List<String>> map = new HashMap<>();
  for (String str : strs) {
    int[] counts = new int[26];
    //统计str中每个字符出现的次数
    for (char c : str.toCharArray()) {
      counts[c-'a'] += 1;
    }
    //构造key
    String key = "";
    for (int i = 0; i < 26; i++) {
      key += "#"+counts[i];
    }
    map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
  }
  return new ArrayList(map.values());
}
```

```java
/**
    对每个string按照字符的字典顺序进行排序，以此作为Hashmap的key
* Time complexity: O(NKlogK): 因为排序的时间复杂度为KlogK
* Space complexity: O(NK)
*/
public List<List<String>> groupAnagrams(String[] strs) {
	HashMap<String, List<String>> map = new HashMap<>();
  for (String str : strs) {
    char[] temp = str.toCharArray();
    Arrays.sort(temp);
    String sort_str = new String(temp);
    map.computeIfAbsent(sort_str, k -> new ArrayList<>()).add(str);
  }
  return new ArrayList(map.values());
}
```

