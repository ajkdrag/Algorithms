/*

  Problem at : https://projecteuler.net/problem=30

*/

public class Problem30 {
    public static void main(String args[]) {
        System.out.println(Solution30.solve(5));
    }
}

class Solution30 {
    static int pow;
    static int res;
    static int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    static int solve(int power){
        pow = power;
        combinationRepetition(10,power+1);
        return res;
    }
    
    static void combinationRepetitionUtil(int chosen[], int arr[], int index, int r, int start, int end) 
    { 
        if (index == r) 
        { 
            int sum = 0;
            int num = 0;
            for (int i = 0; i < r; i++) 
                {
                    int val = arr[chosen[i]];
                    num = num*10 + val;
                    sum += calcPow(val);
                }
                if(sum < num || sum == 0 || sum == 1) return;
                if(calcPowSumDigits(sum) == sum){
                    res += sum;
                } 
            return; 
        } 
        for (int i = start; i <= end; i++) 
        { 
            chosen[index] = i; 
            combinationRepetitionUtil(chosen, arr, index + 1, r, i, end); 
        } 
        return; 
    } 
      
    static void combinationRepetition(int n, int r) 
    { 
        int[] chosen = new int[r+1]; 
        combinationRepetitionUtil(chosen, arr, 0, r, 0, n-1); 
    } 
    
    static int calcPow(int n){
        int res = 1;
        for(int i = 0; i < pow; ++i){
            res *= n;
        }
        return res;
    }
    
    static int calcPowSumDigits(int num){
        int sum = 0;
        while(num > 0){
            sum += calcPow(num%10);
            num/=10;
        }
        return sum;
    }
}


