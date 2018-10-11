/*

  Problem at : https://projecteuler.net/problem=32

*/

import java.util.HashSet;
public class Problem32 {
    public static void main(String args[]) {
       Solution32.solve();
       System.out.println(Solution32.sum);
    }
}

class Solution32 {
    static int[] arr = {0,0,0,0,0,0,0,0,0,0};
    static HashSet<Integer> results = new HashSet<>();
    static int sum = 0;
    static void solve() {
        // 3 x 2
        for(int i = 123; i <= 987; ++i){
            if(i%10 == 0) continue;
            boolean mul = true;
            reset();
            int cpy = i;
            while(cpy > 0){
                int d = cpy%10;
                if(d == 0 || arr[d] != 0){
                    mul = false;
                    break;
                }
                cpy /= 10;
                arr[d] = 1;
            }
            
            if(mul) {
                for(int j = 12; j <= 98; ++j){
                    if(i%10 == 0) continue;
                    mul = true;
                    cpy = j;
                    while(cpy > 0){
                        int d = cpy%10;
                        if(d == 0 || arr[d] != 0){
                            mul = false;
                        }
                        cpy /= 10;
                        arr[d] += 1;
                    }
                    if(mul) multiply(i,j);
                    reset(j);
                }
            }
        }
        // 4 x 1
        for(int i = 1234; i <= 9876; ++i){
            boolean mul = true;
            reset();
            int cpy = i;
            while(cpy > 0){
                int d = cpy%10;
                if(d == 0 || arr[d] != 0){
                    mul = false;
                    break;
                }
                cpy /= 10;
                arr[d] = 1;
            }
            
            if(mul) {
                for(int j = 1; j <= 9; ++j){
                    mul = true;
                    if(arr[j] != 0){
                        mul = false;
                    }
                    arr[j] += 1;
                    if(mul) multiply(i,j);
                    reset(j);
                }
            }
        }
    }
    
    static void reset(){
        for(int i = 9; i>= 0; --i) arr[i] = 0;
    }
    
    static void reset(int x){
        while(x > 0){
            int d = x%10;
            x /= 10;
            arr[d] -= 1;
        }
    }
    
    static void multiply(int a, int b) {
        int prod = a*b;
        if(prod%10 == 0) return;
        int res = prod;
        boolean found = true;
        while(res > 0){
            int d = res%10;
            if(d == 0 || arr[d] != 0){
                found = false;
            }
            res /= 10;
            arr[d] += 1;
        }
        reset(prod);
        if(found && !results.contains(prod)) {
            results.add(prod);
            sum += prod;
        }
    }
}


