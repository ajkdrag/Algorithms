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
        int i, j, k;
        
        for (i = 2; i <= limit-3; ++i)
        {
            j = i;
            if(arr[j] == 4){
                if(arr[j+1] == 4 && arr[j+2] == 4 && arr[j+3] == 4){
                    System.out.println(j);
                    break;
                } 
            }
            if (arr[j] == 0) 
                for (j<<=1; j <= limit; j += i) arr[j]++;
        }
    }
}


