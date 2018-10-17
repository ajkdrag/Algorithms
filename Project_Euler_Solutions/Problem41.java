/*

  Problem at : https://projecteuler.net/problem=41

*/

public class Problem41 {
    public static void main(String args[]) {
        Solution41.solve();
    }
}

class Solution41 {
    static int[][] mask = {{1,3,7},{7,6,5,4,3,2},{7,6,5,4,2,1},{6,5,4,3,2,1}};
    
    static void solve(){
        int max = 0;
        // for 7-pandigital        
        for(int i = 0; i <= 2; ++i){
            int[] choose = mask[i+1];
            int lsd = mask[0][i];
            int res = getLargest(7,choose,lsd);
            if(res > max) max = res;
        }
        System.out.println(max);
        // had there been no solution for 7-pandigital, we would have to go down and check for 4-pandigital.
        // but since there was a solution for 7-pandigital, we don't need to check further.
    }
    
    static int getLargest(int digits,int[] choose, int lsd){ 
        --digits;
        int[] arr = new int[digits];
        int i = digits-1;
        int j = choose.length - 1;
        while(i >= 0) arr[i--] = choose[j--];
            
        int max = 0;
        
        do {
            int num =getNumRep(arr);
            max = num*10 + lsd;
            if(isPrime(max)) return max;
        }
        while(previousPermutation(arr));
        return -1;
    }
    
    // previous permutation algo from : https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
    static boolean previousPermutation(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] <= array[i])
            i--;
        
         if (i <= 0)
            return false;
        
        int j = array.length - 1;
        while (array[j] >= array[i - 1])
            j--;
        
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;
        
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        
        return true;
    }
    static boolean isPrime(int n){
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        for(long i = 6; i*i <= n; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }
    
    static int getNumRep(int[] array){
        int num = 0;
        for(int x : array) num = num*10 + x;
        return num;
    }
}


