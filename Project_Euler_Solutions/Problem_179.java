/*
  Problem at : https://projecteuler.net/problem=179
*/

public class Problem179 {
    public static void main(String args[]) {
        int n = 10000000;
        int[] divisors = new int[n + 1];
        for(int i = 2; i <= n; ++i){
            for(int j = i, lim = n/i; j <= lim; ++j){
                divisors[i*j] += j == i ? 1 : 2;
            }
        }
        int res = 0;
        for(int i = 2; i < n; ++i){
            if(divisors[i] == divisors[i + 1])
                ++res;
        }
        System.out.println("Result : " + res);
    }
}


