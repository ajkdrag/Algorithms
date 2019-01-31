/*
  Problem at : https://projecteuler.net/problem=120
*/

public class Problem120 {
    public static void main(String args[]) {
        int res = 0;
        for(int a = 3; a <= 1000; ++a){
            res += a*(a - 2 + (a&1));
        }
        System.out.println(res);
    }
}


