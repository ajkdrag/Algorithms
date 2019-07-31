/*
  Problem at : http://codeforces.com/contest/710/problem/E
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long X = sc.nextLong();
        long Y = sc.nextLong();
        Sol sol = new Sol(N, X, Y);
        sol.solve();
    }
}
 
class Sol {
    long N, X, Y;
    long[] res;
    
    Sol(long N, long X, long Y){
        this.N = N;
        this.X = X;
        this.Y = Y;
        res = new long[(int)N+1];
        res[1] = X;
        if(N >= 2)
            res[2] = (X<<1) < (X+Y) ? (X<<1) : (X+Y);
    }
    
    long rec(long x){
        if(res[(int)x] > 0){
            return res[(int)x];
        }
        
        if((x&1)==1){
            long t1 = rec(x-1) + X;
            long t2 = rec((x+1)>>1) + X + Y;
            return res[(int)x] = t1 < t2 ? t1 : t2;
        }
        else {
            long min = (x>>1)*X;
            min = (Y < min ? Y : min);
            return res[(int)x] = rec(x>>1)+min;
        }
    }
    
    void solve(){
        System.out.println(rec(N));
    }
}

