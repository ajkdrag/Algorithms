/*

  Problem at : https://projecteuler.net/problem=34

*/

public class Problem34 {
    public static void main(String args[]) {
        System.out.println(Solution34.solve());
    }
}

class Solution34 {
    static int pow;
    static int res;
    static int[] fact = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    
    static int solve(){
        combinationRepetition(10,7);
        return res;
    }
    
    static void combinationRepetitionUtil(int chosen[], int index, int r, int start, int end) 
    { 
        if (index == r) 
        { 
            int sum = 0;
            int num = 0;
            boolean startCounting = false;
            for (int i = 0; i < r; i++) 
                {
                    int val = chosen[i];
                    num = num*10 + val;
                    if(val > 0) startCounting = true;
                    if(startCounting) sum += fact[val];
                }
                if(sum < num || sum < 10) return;
                if(calcFactSum(sum) == sum){
                    res += sum;
                } 
            return; 
        } 
        for (int i = start; i <= end; i++) 
        { 
            chosen[index] = i; 
            if(chosen[0] == 2) return;
            if(chosen[0] == 1 && chosen[1] == 5) return;
            combinationRepetitionUtil(chosen, index + 1, r, i, end); 
        } 
        return; 
    } 
      
    static void combinationRepetition(int n, int r) 
    { 
        int[] chosen = new int[r]; 
        combinationRepetitionUtil(chosen, 0, r, 0, n-1); 
    } 
    
    static int calcFactSum(int num){
        int sum = 0;
        while(num > 0){
            sum += fact[num%10];
            num/=10;
        }
        return sum;
    }
}


