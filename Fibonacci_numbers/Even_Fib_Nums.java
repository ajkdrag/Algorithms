/*
  Problem at : https://www.hackerrank.com/contests/projecteuler/challenges/euler002/problem
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            long n = in.nextLong();
            solve(n);
        }
    }

    static void solve(long N){
        long a = 0;
        long b = 2;
        long sum = b;
        while(a + (b<<2) <= N){
            b = (b<<2) + a;
            a = (b - a)>>2;
            sum += b;
        }
        System.out.println(sum);
    }
}


