/*
  Problem at : https://codeforces.com/contest/676/problem/C
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");
        Sol sol = new Sol(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        sol.solve(br.readLine());        
    }
}

class Sol {
    int k, n;
    Sol(int n, int k){
        this.n = n;
        this.k = k;
    }
    
    void solve(String s){
        char[] arr = s.toCharArray();
        int best1 = 0;
        int curr1 = 0;
        int curr2 = 0;
        int best2 = 0;
        int l1 = 0;
        int l2 = 0;
        int r = 0;
        while(r < n){
            char c = arr[r];
            if(c == 'b')
                ++curr1;
            if(c == 'a')
                ++curr2;
            if(curr1 > k){
                best1 = Math.max(best1, r-l1);
                while(l1 < n && arr[l1] != 'b')
                    ++l1;
                --curr1;
                ++l1;
            }
            
            if(curr2 > k){
                best2 = Math.max(best2, r-l2);
                while(l2 < n && arr[l2] != 'a')
                    ++l2;
                --curr2;
                ++l2;
            }
            ++r;
        }
        best1 = Math.max(best1, r-l1);
        best2 = Math.max(best2, r-l2);
        System.out.println(best1 > best2 ? best1 : best2);
    }
}

