/*

  Problem at : https://projecteuler.net/problem=31

*/

public class Problem31 {
    public static void main(String args[]) {
        System.out.println(Solution31.solve(200));
    }
}

class Solution31 {
    static int[] S = {1,2,5,10,20,50,100,200};
    static int[][] dp;
    static int solve(int n){
        int cols = S.length;
        int rows = n+1;
        int i = rows-1,j = cols - 1;
        dp = new int[rows][cols];
        for(; j>=0;j--){
            dp[0][j] = 1;
        }
        for(; i>=0;i--){
            dp[i][0] = 1;
        }
        for(i = 1; i < rows; ++i){
            for( j = 1; j < cols; ++j){
                dp[i][j] = dp[i][j-1];
                if(S[j] <= i) dp[i][j] += dp[i - S[j]][j];
            }
        }
        return dp[i-1][j-1];
    }
}


