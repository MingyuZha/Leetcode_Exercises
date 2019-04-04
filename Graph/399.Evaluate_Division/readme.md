# Leetcode 399. Evaluate Division

## Description

Equations are given in the format `A / B = k`, where `A` and `B` are variables represented as strings, and `k` is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return `-1.0`.

**Example:**
Given `a / b = 2.0, b / c = 3.0.` 
queries are: `a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .` 
return `[6.0, 0.5, -1.0, 1.0, -1.0 ].`

The input is: `vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries`, where `equations.size() == values.size()`, and the values are positive. This represents the equations. Return `vector<double>`.

According to the example above:

```
equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
```

## Solution 

我们可以把```a/b = k```想象成node a和node b之间有一条有向边，the weight from a to b is k. 相反的，b到a也是连通的，权重为1/k.

```java
public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
  /**
  * pairs存储边信息
  * a: b -> c -> d
  * b: a -> d
  * c: a
  * d: a -> b
  */
  HashMap<String, ArrayList<String>> pairs = new HashMap<String, ArrayList<String>>();
  //valuesPair存储边的权重
  HashMap<String, ArrayList<Double>> valuesPair = new HashMap<String, ArrayList<Double>>();
  /**
  * 构造graph的过程
  */
  for (int i = 0; i < equations.length; i++) {
    String[] equation = equations[i];
    if (!pairs.containsKey(equation[0])) {
      pairs.put(equation[0], new ArrayList<String>());
      valuesPair.put(equation[0], new ArrayList<Double>());
    }
    if (!pairs.containsKey(equation[1])) {
      pairs.put(equation[1], new ArrayList<String>());
      valuesPair.put(equation[1], new ArrayList<Double>());
    }
    pairs.get(equation[0]).add(equation[1]);
    pairs.get(equation[1]).add(equation[0]);
    valuesPair.get(equation[0]).add(values[i]);
    valuesPair.get(equation[1]).add(1/values[i]);
  }
	/** 
	* 使用dfs计算query的output的过程
	*/
  double[] result = new double[queries.length];
  for (int i = 0; i < queries.length; i++) {
    String[] query = queries[i];
    result[i] = dfs(query[0], query[1], pairs, valuesPair, new HashSet<String>(), 1.0);
    if (result[i] == 0.0) result[i] = -1.0;
  }
  return result;
}

private double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> values, HashSet<String> set, double value) {
  if (set.contains(start)) return 0.0; //如果已访问过点集中包含了start，直接返回0
  if (!pairs.containsKey(start)) return 0.0; //如果pairs中没有为start的key，返回0
  if (start.equals(end)) return value; //如果start和end是相同的字符，直接返回value即可
  set.add(start); //使用set集合来记录访问过的node，防止陷入死循环

  ArrayList<String> strList = pairs.get(start);
  ArrayList<Double> valueList = values.get(start);
  double tmp = 0.0;
  for (int i = 0; i < strList.size(); i++) {
    tmp = dfs(strList.get(i), end, pairs, values, set, value*valueList.get(i));
    if (tmp != 0.0) {
      break;
    }
  }
  set.remove(start);
  return tmp;
}
```

