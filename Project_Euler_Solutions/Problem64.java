/*

  Problem at : https://projecteuler.net/problem=64

*/

public class Problem64 {
    public static void main(String args[]) {
        System.out.println(Solution64.solve());        
    }
}

class Solution64 {
    static int solve(){
        int count = 1;
        int sqrt = 2;
        int limit = 10000;
        for(int i = 2; i <= limit; ++i){
            if(i + 2 == sqrt*sqrt) {
                i += 4;
                ++sqrt;
                if(i <= limit) ++count;
                continue;
            }
            if((i&3) == 0) continue;
            if((period_len(i,sqrt-1) & 1) == 1) ++count;
        }
        return count;
    }
    
    static int period_len(int n,int sqrt){
        int a_0, b_0, c_0, a, b, c, result=0;
        a_0 = sqrt;
        b = b_0 = a_0;
        c = c_0 = n - a_0*a_0;
        do {
            a= (a_0 + b)/c;
            b= a*c - b;
            c= (n - b*b)/c;
            result++;
        } while ((b != b_0) || (c != c_0));
        return result;
    }
}


