/*

  Problem at : https://projecteuler.net/problem=63

*/

public class Problem63 {
    public static void main(String args[]) {
        int res = 0;
        for(int i = 1; i <= 9; ++i){
             res += (int)(1/(1 - Math.log10(i)));
        }
        System.out.println(res);
    }
}


