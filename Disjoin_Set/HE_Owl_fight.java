/*
  Problem at : https://www.hackerearth.com/practice/data-structures/disjoint-data-strutures/basics-of-disjoint-data-structures/practice-problems/algorithm/owl-fight/
*/

import java.util.*;
import java.io.*;

public class TestClass {
    static int[] arr;
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        arr = new int[N+1];
        for(int i= 1; i <= N; ++i)
            arr[i] = -i;
        while(M-- > 0)
            union(sc.nextInt(), sc.nextInt());
        M = sc.nextInt();
        while(M-- > 0){
            System.out.println(solve(sc.nextInt(), sc.nextInt()));
        }
    }
    
    static int find(int n){
        if(arr[n] < 0)
            return n;
        int x = find(arr[n]);
        arr[n] = x;
        return x;
    }
    
    static void union(int a, int b){
        int par_a = find(a);
        int par_b = find(b);
        if(par_a == par_b) 
            return;
        int str_a = arr[par_a];
        int str_b = arr[par_b];
        if(str_a < str_b){
            str_a += str_b;
            arr[par_b] = par_a;
        }
        else {
            str_b += str_a;
            arr[par_a] = par_b;
        }
    }
    
    static String solve(int a, int b){
        int par_a = find(a);
        int par_b = find(b);
        if(par_a == par_b)
            return "TIE";
        int str_a = arr[par_a];
        int str_b = arr[par_b];
        return str_a < str_b ? Integer.toString(a) : Integer.toString(b);
    }
}


