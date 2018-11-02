/*

  Problem at : https://projecteuler.net/problem=58

*/

public class Problem58 {
    public static void main(String args[]) {
        Solution58.solve();
    }
}

class Solution58 {
    
    static void solve(){
        int count = 0;
        for(int s = 3; ; s+=2){
            int num1 = s*s - s + 1;
            int num2 = num1 - (s - 1);
            int num3 = num2 - (s - 1);
            if(isPrime(num1)){
                ++count;
            }
            if(isPrime(num2)){
                ++count;
            }
            if(isPrime(num3)){
                ++count;
            }
            if(count*10 < ((s<<1)-1)){
                System.out.print(s);
                break;
            }
        }
    }
    
    static boolean isPrime(int n) {
        if(n == 2 || n == 3) return true;
        if((n&1) == 0 || n%3 == 0) return false;
        for(int i = 6; i*i <= n; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }
}


