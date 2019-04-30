/*
  Problem at : http://codeforces.com/contest/630/problem/I
  
  It can be observed that it's a combinatorial problem. Few observations :
    1) The total number of ways to have 'n' successive cars = (n - 1)
    2) Out of these one way will have the 1st car at the leftmost end, and another way will have last car at rightmost end
    3) For each of these two cases, the number of ways = 4*3*4^(n - 3) since the 'n' successive cars can be any one of the 4 makes
       Also the car just next to this series of 'n' successive cars, can't be of the same make. Hence, for that car, the number of
       choices is only 3 (and not 4). While for the remainder (n - 3) cars, each has a choice of 4, thereby yielding 4^(n - 3)
    4) Now, for the cases where this 'n' successive car series lies in the middle, we will need to make sure that cars that lie next
       to either end of the series shouldn't be of the same make as the series. Rest logic is the same as in 3). Thus for these cases
       we have, number of ways = 4*3*3*4^(n - 4)
    5) Summing up, we get total number of ways = 2*(4*3*4^(n - 3)) + (n - 1 - 2)*(4*3*3*4^(n - 4)) = (9*n - 3)*4^(n - 3)
    
  We see that we need to perform an exponentiation as well, thus we can apply binary exponentiation. Moreover since the base is 4,
  i.e a power of 2, we can use bit shifts instead of multiplication. (For convenience, we can write 4^(n - 3) as 2^(2*n - 6)
  
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);
        long res = (9*n - 3)*binaryExp(2, (n<<1) - 6);
        System.out.println(res);
    }
    
    static long binaryExp(int base, int exp){
        if(exp == 0)
            return 1;
        long half = binaryExp(base, exp>>1);
        half <<= (exp>>1);
        if((exp&1)== 1)
            half <<= 1;
        return half;
    }
}



