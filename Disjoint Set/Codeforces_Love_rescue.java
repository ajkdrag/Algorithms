/*
  Problem at : https://codeforces.com/contest/939/problem/D
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        for(int i = 0; i < n; ++i){
            sol.union(a[i], b[i]);
        }
        sol.solve();
    }
}

class Solution {
    int[] par;
    int[] sz;
    
    Solution(){
        par = new int[26];
        sz = new int[26];
        for(int i = 0; i < 26; ++i){
            par[i] = i;
            sz[i] = 1;
        }
    }
    
    void union(char a, char b){
        int _a = a-'a';
        int _b = b-'a';
        int parA = findPar(_a);
        int parB = findPar(_b);
        if(parA == parB)
            return;
        if(sz[parA] < sz[parB]){
            int temp = parA;
            parA = parB;
            parB = temp;
        }
        par[parB] = parA;
        sz[parA] += sz[parB];
        sz[parB] = 1;
    }
    
    int findPar(int a){
        if(par[a] == a)
            return a;
        return par[a] = findPar(par[a]);
    }
    
    void solve(){
        String res = "";
        int ct = 0;
        for(int i = 0; i < 26; ++i){
            if(par[i] != i){
                char char_a = (char)(i + 'a');
                char char_b = (char)(par[i] + 'a');
                res += char_a + " " + char_b + "\n";
            }
            ct += (sz[i] - 1);
        }
        System.out.println(ct);
        System.out.println(res);
    }
}

