/*

  Problem at : https://projecteuler.net/problem=53

*/

public class Problem53 {
    public static void main(String args[]) {
        Solution53.solve();
    }
}

class Solution53 {
    static int[][] dp;
    
    static void solve(){
        dp = new int[78][11];
        // 0 means 23, 1 means 24 and so on...
        dp[0][0] = 1;
        dp[0][1] = 23;
        dp[0][2] = 253;
        dp[0][3] = 1771;
        dp[0][4] = 8855;
        dp[0][5] = 33649;
        dp[0][6] = 100947;
        dp[0][7] = 245157;
        dp[0][8] = 490314;
        dp[0][9] = 817190;
        dp[0][10] = 1144066;
        
        int min = 9;
        int j = min;
        int res = 4;
        for(int i = 1; i <= 77; ++i){
            dp[i][0] = 1;
            boolean found_min = false;
            while(j>=1){
                int val = dp[i-1][j-1] + dp[i-1][j];
                dp[i][j] = val;
                if(!found_min && val < 1000000){
                    found_min = true;
                    min = j;
                }
                --j;
            }
            j = min;
            res += (i+24)-((j+1)<<1);
        }

        System.out.println(res);
    }
}


