/*

  Problem at :  https://projecteuler.net/problem=52

*/

public class Problem52 {
    public static void main(String args[]) {
        Solution52.solve();
    }
}

class Solution52 {
    static long mask;
    
    static int solve(){
        int start = 100008;
        for(; start <= 169999; start += 9){
            boolean found = true;
            for(int i = 6; i >= 2; --i){
                if(!isPerm(start,i*start)){ 
                    found = false;
                    break;
                }
            }
            if(found){
                System.out.println(start);
                return start;
            }
        }
        // no 6-digit number possible, move onto 7-digit?
        return -1;
    }
    
    static boolean isPerm(int a, int b){
        int r = 0;
        mask = 0L;
        while(a > 0){
            r = a%10;
            mask += (1<<(r<<2));
            a/=10;
           
        }
        
        while(b > 0){
            r = b%10;
            mask -= (1<<(r<<2));
            b/=10;
        }
        
        if(mask == 0L) return true;
        return false;
    }
}


