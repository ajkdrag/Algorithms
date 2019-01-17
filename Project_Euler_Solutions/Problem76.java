/*
  Problem at : https://projecteuler.net/problem=76
*/

public class Problem100 {
    public static void main(String args[]) {
        int target = 100;
        int[] ways = new int[target + 1];
        ways[0] = 1;
         
        for (int i = 1; i <= 99; i++) {
            for (int j = i; j <= target; j++) {
                ways[j] += ways[j - i];
            }
        }
        
        System.out.println(ways[target]);
    }
}


