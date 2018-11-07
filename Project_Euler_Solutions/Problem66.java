/*

  Problem at : https://projecteuler.net/problem=66

*/

public class Problem66 {
    public static void main(String args[]) {
        System.out.println(Solution66.solve());
    }
}

class Solution66 {
    static int solve(){
        int sqrt = 2;
        int limit = 1000;
        int max = 0;
        int index = -1;
        for(int i = 2; i <= limit; ++i){
            if(i+2 == sqrt*sqrt) {
                i += 4;
                ++sqrt;
                continue;
            }
            if((i&3) == 0) continue;
            int len = period_len(i,sqrt-1);
            if((len&1) == 1) len = (len<<1) - 1;
            if(len >= max){
                max = len;
                index = i;
            } 
        }
        return index;
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



