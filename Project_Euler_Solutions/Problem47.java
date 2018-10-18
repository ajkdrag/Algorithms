/*

  Problem at : https://projecteuler.net/index.php?section=problems&id=47

*/

public class Problem47 {
    public static void main(String args[]) {
        Solution47.solve();
    }
}

class Solution47 {
    static void solve(){
        int limit = 150000;
        int[] arr = new int[limit+1];
        int i, j, k = 0;
        
        for (i = 2; k < 4 && i <= limit; ++i)
        {
            j = i;
            if(arr[j] == 4){
                ++k;
            }
            else{
                k = 0;
                if (arr[j] == 0) 
                    for (j<<=1; j <= limit; j += i) arr[j]++;
            }
        }
        if(i <= limit) System.out.println(i - 4);
    }
}


