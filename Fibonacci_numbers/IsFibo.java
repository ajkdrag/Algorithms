/*
  Problem at : https://www.hackerrank.com/contests/codesprint5/challenges/is-fibo
*/


import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    static long a = -1;
    static long b = -1;
    static long c = -1;
    static long d = -1;
    private static final long MOD = 10000000000L;
    private static long key;
    private static boolean overflow = false;
    
    static String solve(long n){
        key = n;
        if(modBSearch(1, n))
            return "IsFibo";
        else
            return "IsNotFibo";
    }
    
     static void fib (long n) {
        if (n == 0){
            a = 0;
            b = 1;
            return;
        }
        fib(n >> 1);
        c = mulMod(a, - a + b*2);
        d = (mulMod(a,a) + mulMod(b,b))%MOD;
        if ((n & 1) == 1){
            a = d;
            b = (c + d)%MOD;
            return;
        }
        a = c;
        b = d;
    }
    
    static long mulMod(long num, long times) {
      if(times < 0)
          times += MOD;
      long temp = num;
      long res = 0;
      while (times > 0) {
        if ((times & 1) == 1){
            if(res + temp > key<<1)
                overflow = true;
            res = (res + temp) % MOD;
        }

        temp = (temp << 1) % MOD;

        times >>= 1;
      }
      return res;
    }
    
    static boolean modBSearch(long low, long high){
        if(key < 4)
            return true;
        while(low <= high){
            long mid = (low + high)>>1;
            overflow = false;
            fib(mid);
            if(overflow){
                high = mid - 1;
                continue;
            }
            if(a == key)
                return true;
            else if(a < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            long n = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = solve(n);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}


