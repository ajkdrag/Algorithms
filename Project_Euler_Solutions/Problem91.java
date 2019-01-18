/*
  Problem at : https://projecteuler.net/problem=91
*/

public class Problem91 {
    public static void main(String args[]) {
        int size = 50;
        int result = size*size*3;
        for (int x = 1; x <= size; x++) {
            for (int y = 1; y <= size; y++) {
                int fact = gcd(x, y);
                result += Math.min(y*fact /x, (size - x)*fact /y) * 2;
            }
        }
        System.out.println(result);
    }
    
    static int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a%b);
    }
}


