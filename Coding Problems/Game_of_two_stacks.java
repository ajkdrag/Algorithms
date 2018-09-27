/*
  Problem at : https://www.hackerrank.com/challenges/game-of-two-stacks/problem
  
  Algo : 
  At first, we greedily see how much we can pop off from individual stacks and store their count in (count_a, count_b)
  We do a prefix sum on the two stacks and stop when the sum exceeds the target.
  The current maximum = the greater of the two counts 
  The start with the stack that had the greater count and do the following : 
    
    We have two pointers : Pointer1 is at the max count of the larger stack.
    Pointer2 is at the topmost element of the smaller stack.
    Take the other stack (with the lower count) and pop 1 element from it. The target now becomes (x - B[0]) 
    assuming element at 0th index implies the topmost element in the stack. (A = prefix_sum of stack1, B = prefix_sum of stack2)
    Pointer2 is now at index 1.
    The prefix sum at Pointer1 if <= target implies that our current maximum has increased by 1, because we were
    able to include one element from the other stack as well. 
    Current maximum += 1
    Pointer1 stays at count_a
    
    Next we do the same thing. Target now becomes (x - B[1]) indicating that we now have 2 elements from stack2
    If prefix sum at Pointer1 i.e A[pointer_1] <= target, implies that we can again include one more element.
    But if it is > target, we don't improve. So shift Pointer1 back (to a smaller value) . i.e Pointer1 -= 1 
    (because prefix sum is always a sorted array, once we know that value at Pointer1 is less than target, 
    we should move Pointer1 backwards, because target is always getting smaller in every iteration)
    We stop iterating when pointer2 reaches count_b indicating we can't take more elements from the other stack.
    
    Note that in each iteration, we are only one value of A with the target. This will find the maximal configuration.
    But there can be more than one maximal configuration. For that we do a binary search in every iteration on A for the index
    that yields the largest value <= target, and our left index will the index of 'A' below which the count < maximum_count
    and right index will be the index obtained from the previous binary search. 
    
*/

import java.io.*;
import java.util.*;

public class Solution {

    static int twoStacks(int x, int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int count_a = 0;
        int count_b = 0;
        List<Integer> prefix_a = new ArrayList<>(n);
        List<Integer> prefix_b = new ArrayList<>(m);

        int total = 0;
        for (int i = 0; i < n; ++i) {
            total += a[i];
            if (total <= x) {
                count_a = i + 1;
                prefix_a.add(total);
            } else
                break;
        }

        total = 0;
        for (int i = 0; i < m; ++i) {
            total += b[i];
            if (total <= x) {
                count_b = i + 1;
                prefix_b.add(total);
            }

            else
                break;
        }
        if (count_a == 0)
            return count_b;
        if (count_b == 0)
            return count_a;

        a = new int[count_a];
        b = new int[count_b];
        for (int i = 0; i < count_a; ++i) {
            a[i] = prefix_a.get(i);
        }
        for (int i = 0; i < count_b; ++i) {
            b[i] = prefix_b.get(i);
        }

        if (count_a < count_b) {
            return compute2(x, b, count_b, a, count_a);
        } else {
            return compute2(x, a, count_a, b, count_b);
        }

    }

    static int compute(int x, int[] a, int count_a, int[] b, int count_b) {
        int res = count_a;
        int l = count_a - 1;
        int r = count_a - 1;

        for (int i = 0; i < count_b; ++i) {
            int target = x - b[i];
            l = Math.max(0, res - i - 2);

            int id = bSearch(a, l, r, target);
            if (id == -1) {
                r = Math.max(0, l - 1);
                continue;
            }
            r = id;
            res = Math.max(res, (i + id + 2));
        }

        return res;
    }
    
    static int compute2(int x, int[] a, int count_a, int[] b, int count_b){
        int res = count_a;
        //int l = count_a - 1;
        int r = count_a - 1;

        for (int i = 0; i < count_b; ++i) {
            int target = x - b[i];
            if(a[r] <= target) res += 1;
            else {
                --r;
            }
        }

        return res;
    }

    static int bSearch(int[] arr, int l, int r, int x) {
        if (arr[r] <= x)
            return r;
        if (arr[l] > x)
            return -1;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            if (arr[mid] <= x)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
         BufferedWriter bufferedWriter = new BufferedWriter(new
         FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(scanner.nextLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            String[] nmx = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmx[0].trim());

            int m = Integer.parseInt(nmx[1].trim());

            int x = Integer.parseInt(nmx[2].trim());

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");

            for (int aItr = 0; aItr < n; aItr++) {
                int aItem = Integer.parseInt(aItems[aItr].trim());
                a[aItr] = aItem;
            }

            int[] b = new int[m];

            String[] bItems = scanner.nextLine().split(" ");

            for (int bItr = 0; bItr < m; bItr++) {
                int bItem = Integer.parseInt(bItems[bItr].trim());
                b[bItr] = bItem;
            }

            int result = twoStacks(x, a, b);
            System.out.println(result);
             bufferedWriter.write(String.valueOf(result));

             bufferedWriter.newLine();
        }

         bufferedWriter.close();
    }
}

