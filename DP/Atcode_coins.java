/*
  Problem at : https://atcoder.jp/contests/dp/tasks/dp_i
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Solution sol = new Solution(N);
        for(int i = 0; i < N; ++i)
            sol.addCoin(i, sc.nextDouble());
        System.out.println(sol.solve());
    }
}

class Solution {
    double[] probs;
    int N;
    double[] dp;
    
    Solution(int N){
        this.N = N;
        this.dp = new double[N+1];
        this.probs = new double[N];
    }
    
    void addCoin(int i, double prob){
        probs[i] = prob;
        if(i == 0){
            dp[1] = probs[0];
            dp[0] = 1 - dp[1];
            return;
        }
        for(int j = N; j >= 1; --j){
            dp[j] = (dp[j]*(1-prob)) + (dp[j-1]*prob);
        }
        dp[0] *= (1 - prob);
    }
    
    double solve() {
        double res = 0;
        for(int i = (N+1)>>1; i <= N; ++i)
            res += dp[i];
        return res;
    }
}


