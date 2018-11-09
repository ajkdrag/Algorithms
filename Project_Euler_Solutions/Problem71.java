/*

  Problem at : https://projecteuler.net/problem=71

*/

public class Problem71 {
    public static void main(String args[]) {
        System.out.println(Solution71.solve(3,7,1000000));        
    }
}

class Solution71 {
    static long solve(long numer, long deno, long limit){
        double frac = numer*1.0/deno;
        long max_n = 1;
        long max_d = limit;
        double temp = 0.0;
        long x = 0L;
        
        while(limit-- > 2){
            temp = (frac*limit);
            x = (long)temp;
            if(x == (long)temp) --x;
            if(x*max_d > max_n*limit){
                max_n = x;
                max_d = limit;
            }
        }
        return max_n;
    }
}

// Using farey sequence property, two consecutive members differ by 1, we have : 

public class Problem71 {
    public static void main(String args[]) {
        System.out.println(Solution71.solve(3,7,1000000));
    }
}

class Solution71 {
    static int solve(int numer, int deno, int limit){
        double temp = 0.0;
        while(limit-- > 2){
            temp = (limit*numer - 1)/deno;
            if(temp == (int)(temp)) break;
        }
        return (int)temp;
    }
}


