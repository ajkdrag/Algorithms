/*

  Problemt at : https://projecteuler.net/problem=45

*/

public class Problem45 {
    public static void main(String args[]) {
        System.out.println(Solution45.solve());        
    }
}

class Solution45 {
    static long solve(){
        long result = 0;
        int i = 143;
         
        while (true) {
            ++i;
            result = i*((i<<1) - 1);
            if (isP(result)) {
                return result;
            }
        }
    }
    
    static boolean isP(long number){
        long t = 1 + 24*number;
        double sq = Math.sqrt(t);
        if(sq != (int)sq) return false;
        t = (int)sq - 5;
        if((t&1) != 0) return false;
        return (t%3) == 0;
    }
}


