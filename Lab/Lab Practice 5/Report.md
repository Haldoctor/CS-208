
# Lab Practice 5
12312109 李泓霖
## Problem 1
### Original Code
```Java
import java.util.PriorityQueue;

public class StoneMerger {

    //In this part I test two testcases.
    //The right result are printed after "Correct Answer".
    public static void main(String[] args) {
        int[] stones1 = {1, 2, 3, 4};
        System.out.println("Test Case 1:");
        System.out.println(minTotalCost(stones1));
        System.out.println("Correct Answer:" + "19");
        System.out.println(maxTotalCost(stones1));
        System.out.println("Correct Answer" + "26");

        int[] stones2 = {6, 5, 4, 3};
        System.out.println("\nTest Case 2:");
        System.out.println(+ minTotalCost(stones2));
        System.out.println("Correct Answer:" + "36");
        System.out.println( + maxTotalCost(stones2));
        System.out.println("Correct Answer:" + "44");
    }

    //Calculation of minimum cost
    public static int minTotalCost(int[] stones) {
        if (stones == null || stones.length < 1) {
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int stone : stones) {
            minHeap.offer(stone);
        }
        return calculateCost(minHeap, false);
    }

    //Calculation of maximum cost
    public static int maxTotalCost(int[] stones) {
        if (stones == null || stones.length < 1) {
            return 0;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) maxHeap.offer(stone);
        return calculateCost(maxHeap, true);
    }

    private static int calculateCost(PriorityQueue<Integer> heap, boolean isMax) {
        int total = 0;
        while (heap.size() > 1) {
            int x = heap.poll();
            int y = heap.poll();
            int sum = x + y;
            total += sum;
            heap.offer(isMax ? sum : sum); 
        }
        return total;
    }
}

```
### Input and Output
**Input**
In this part of code, I combined input into `main` part of the code.
```Java
public static void main(String[] args) {
        int[] stones1 = {1, 2, 3, 4};
        System.out.println("Test Case 1:");
        System.out.println(minTotalCost(stones1));
        System.out.println("Correct Answer:" + "19");
        System.out.println(maxTotalCost(stones1));
        System.out.println("Correct Answer" + "26");

        int[] stones2 = {6, 5, 4, 3};
        System.out.println("\nTest Case 2:");
        System.out.println(+ minTotalCost(stones2));
        System.out.println("Correct Answer:" + "36");
        System.out.println( + maxTotalCost(stones2));
        System.out.println("Correct Answer:" + "44");
    }
```
**Output**
```
Test Case 1:
19
Correct Answer:19
26
Correct Answer26

Test Case 2:
36
Correct Answer:36
44
Correct Answer:44
```
### Explanation
In this problem I use `Greedy-Algorithm` to solve this problem. Considering that in this algorithm we need to add up two minimum piles of stones together each time, so we have to order the piles of stone each time after an execution which add up two minimum piles of stones. Considering we need to update the order after each execution, I use `min-heap` and `max-heap` to arrange the data. After execution we delete the minimum node or maximum node and process the next execution.

## Problem 2
### Original Code
```Java
import java.util.*;

public class StoneMergerPlus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            List<Integer> piles = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                piles.add(scanner.nextInt());
            }
            int minCost = MinTotalCost(piles, n, k);
            int maxCost = MaxTotalCost(piles);
            System.out.println(minCost + " " + maxCost);
        }
        scanner.close();
    }

    private static int MinTotalCost(List<Integer> piles, int n, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int pile : piles) minHeap.add(pile);

        int totalCost = 0;
        int x = (n - 1) % (k - 1) + 1;

        if (x > 1) {
            int mergeCost = 0;
            for (int i = 0; i < x; i++) {
                mergeCost += minHeap.poll();
            }
            minHeap.add(mergeCost);
            totalCost += mergeCost;
        }

        while (minHeap.size() > 1) {
            int mergeCost = 0;
            for (int i = 0; i < k; i++) {
                if (minHeap.isEmpty()) break;
                mergeCost += minHeap.poll();
            }
            minHeap.add(mergeCost);
            totalCost += mergeCost;
        }
        return totalCost;
    }

    private static int MaxTotalCost(List<Integer> piles) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int pile : piles) maxHeap.add(pile);

        int totalCost = 0;
        while (maxHeap.size() > 1) {
            int a = maxHeap.poll();
            int b = maxHeap.poll();
            int sum = a + b;
            totalCost += sum;
            maxHeap.add(sum);
        }
        return totalCost;
    }
}

```
### Input and Output
In this algorithm, I didn't combined input with the program due to the variable `T`, `n` and `k`. In this program, `T`, `n` and `k` is changeable
**Input**
```Java
2 // The number of testcase is 2
4 3 // There are 4 piles of stones, and the max piles each execution can be processed is 2
1 2 3 4 // The number of each piles
4 3 // There are 4 piles of stones, and the max piles each execution can be processed is 2
6 5 4 3 // The number of each piles
```
**Output**
```Java
13 26 // Answer of testcase 1
25 44 // Answer of testcase 2
```
### Explanation
In this problem I also use `Greedy-Algorithm`. Considering that this time each execution the number of piles add up is not certain. I find out that the last time of the execution is better if there are just `k` piles left.

Assump that each time we combine `k` piles stones, and after `t` times of combination, there will left k piles of stones. So we can have the equation as follows:

$$
n-k=t*(k-1)
$$

If this cannot be divided, then we have to adjust the piles we add up at the first combination. And the first combination's number of piles satisfy the follow equation:

$$
x=((n-1)mod(k-1))+1
$$

According to the equations above, we can calculate the how many piles we need to add up if we want to have the minimum or maximum results.
