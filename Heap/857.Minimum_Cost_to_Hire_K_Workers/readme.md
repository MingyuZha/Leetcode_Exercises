# Leetcode 857. Minimum Cost to Hire K Workers

## Description

There are `N` workers.  The `i`-th worker has a `quality[i]` and a minimum wage expectation `wage[i]`.

Now we want to hire exactly `K` workers to form a *paid group*.  When hiring a group of K workers, we must pay them according to the following rules:

1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
2. Every worker in the paid group must be paid at least their minimum wage expectation.

Return the least amount of money needed to form a paid group satisfying the above conditions.

**Example 1:**

```
Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
```

**Example 2:**

```
Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
```

## Solution

#### 暴力解法

```ratio```：表示worker的```wage```与```quality```之间的比值，即每一点quality所值的wage，例如，worker A的期望薪资是100，他的工作质量为20，那么worker A的**ratio**就为100 / 20 = 5

暴力解法就是我们遍历以每一个worker的ratio作为其余所有工人的薪酬支付ratio，当然了，**这样做需要满足一个条件，那就是当前工人的ratio必须是这些工人中最高的**，否则的话，我们将无法满足每个工人得到的薪酬至少达到自己的期望薪资这一限制条件。

```java
public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
  int N = quality.length;
  double ans = 1e9;

  for (int captain = 0; captain < N; ++captain) {
    // Must pay at least wage[captain] / quality[captain] per qual
    double ratio = (double) wage[captain] / quality[captain];
    double prices[] = new double[N]; //使用一个数组去存储以当前ratio支付工资，其余每个工人的实际到手工资
    int t = 0;
    for (int worker = 0; worker < N; ++worker) {
      double price = ratio * quality[worker];
      if (price < wage[worker]) continue; //只有当工人实际到手薪酬不低于其期望薪酬时，我们才将其纳入考虑范围
      prices[t++] = price;
    }

    if (t < K) continue; //如果以当前ratio作为支付薪酬的基准无法招聘满K个人，说明我们不能以当前工人的ratio作为标准
    Arrays.sort(prices, 0, t); //将所有纳入考虑范围内的工人实际到手工资升序排序
    double cand = 0;
    for (int i = 0; i < K; ++i) //计算前K个工人的工资总和
      cand += prices[i];
    ans = Math.min(ans, cand);
  }

  return ans;
}
```

> 时间复杂度：O(N(N + NlogN + N)) = O(2N^2 + N^2logN) -> O(N^2logN)
>
> 空间复杂度：O(N)

#### 使用Heap的解法

延续了前面暴力解法中遍历以每个工人的```ratio```作为支付工资标准的想法，我们考虑可以将所有工人先按照```ratio```升序排序，这样一来，在遍历的过程中，我们就可以确保以当前工人的```ratio```来支付工资肯定可以满足在他前面所有工人的期望薪资要求，那么这样一来，我们接下来只需要找出在他之前(包括他自己)所有工人里```quality```最小的```K```个人就行了，因为我们的```ratio```已经定下来了，要计算实际支付的工资总数我们只需要知道K个工人的```quality```总和即可，**要想使得支付的工资总数达到最小，那么我们只需要使得K个工人的**```quality```**总和最小即可**。

```java
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = new Worker[quality.length];
        for (int i = 0; i < workers.length; i++) workers[i] = new Worker(quality[i], wage[i]);
        Arrays.sort(workers); //将所有工人按照ratio升序排序
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int sum_heap = 0; //用来记录当前heap中所有工人的quality之和
        double ans = Double.MAX_VALUE;
        for (int i = 0; i < workers.length; i++) {
          /**
          * 当heap中的工人数小于指定的K，或者当当前工人的quality能够排进最小的K个quality中时，我们做插入heap的操作
          * 当heap的大小正好等于K时，我们计算当前需要支付的工资总数，并与ans比较
          */
            if (heap.size() < K || workers[i].quality > heap.peek()) { 
                if (heap.size() == K) sum_heap += heap.poll();
                sum_heap += workers[i].quality;
                heap.offer(-workers[i].quality);
                if (heap.size() == K) ans = Math.min(ans, sum_heap * workers[i].ratio());
            }
        }
        return ans;
    }
    public class Worker implements Comparable<Worker> {
        int quality, wage;
        Worker(int q, int w) {
            quality = q;
            wage = w;
        }
        public double ratio() {
            return (double) wage / quality;
        }
        @Override
        public int compareTo(Worker o) {
            return Double.compare(this.ratio(), o.ratio());
        }
    }
}
```

