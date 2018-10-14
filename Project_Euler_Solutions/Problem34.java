/*

  Problem at : https://projecteuler.net/problem=34

*/

public class Problem34 {
    public static void main(String args[]) {
        System.out.println(Solution34.solve());
    }
}

class Solution34 {
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


/*

  Non-recursive solution (same as above, but generating of combinations is done iteratively)

*/

public class Problem34 {
    public static void main(String args[]) {
        System.out.println(Solution34.solve());
    }
}

class Solution34 {
    static int[] fact = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    static int solve(){
        int[] chosen =  new int[7];
        int limit = 11440;
        int res = 0;
        while(limit-- > 0){
            int sum = 0;
            int num = 0;
            boolean startCounting = false;
            for (int i = 0; i < 7; ++i){
                int val = chosen[i];
                num = num*10 + val;
                if(val > 0) startCounting = true;
                if(startCounting) sum += fact[val];
            }
            increment(chosen);
            if(sum < num || sum < 10) continue;
            if(calcFactSum(sum) == sum){
                res += sum;
            } 
            if(chosen[0] == 1 && chosen[1] == 5) break;
        }
        return res;
    }
    
    static void disp(int[] chosen){
        for(int i = 0; i < chosen.length; ++i) System.out.print(chosen[i] + " ");
    }
    
    static void increment(int[] chosen){
        int carry = 1;
        int x = -1;
        int s = -1;
        for(int i = chosen.length - 1; i >= 0; --i){
            int val = chosen[i];
            val += carry;
            carry = 0;
            if(val == 10){
                val = 0;
                carry = 1;
            }
            chosen[i] = val;
            if(carry == 0) {
                s = chosen.length - 1 - i;
                x = val;
                break;
            }
        }
        
        int extraJumps = calcExtraJumps(x,s);
        if(extraJumps!=0) increment(chosen, extraJumps);
    }
    
    static int calcExtraJumps(int x, int s){
        if(s == 0) return 0;
        if(s == 1) return x;
        
        int temp = ((int)Math.pow(10,s)) - 1;
        return (temp/9)*x;
    }
    
    static void increment(int[] chosen, int carry){
        for(int i = chosen.length - 1; i >= 0; --i){
            int val = chosen[i];
            val += carry;
            chosen[i] = val%10;
            carry = val/10;
            if(carry == 0) break;
        }
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


