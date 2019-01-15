/*
  Problem at : https://projecteuler.net/problem=92
*/

// brute-force
public class Problem {
    static int counter = 0;
    public static void main(String args[]) {
        for(int i = 1; i < 10000000; ++i){
            getFamily(i);
        }
        System.out.println(counter);
    }
    static void getFamily(int n){
        while(true){
            int digit_sum = 0;
            while(n > 0){
                int d = n%10;
                n/=10;
                digit_sum += d*d;
            }
            if(digit_sum == 89){
                ++counter;                
                break;
            }
            if(digit_sum == 1)
                break;
            n = digit_sum;
        }
    }
}

// tried optimizing (exec time hardly improved though)
import java.util.HashSet;
import java.util.Stack;

public class Problem92 {
    static HashSet<Integer> set_1;
    static HashSet<Integer> set_89;
    static Stack<Integer> family_stack;
    static int res;
    
    public static void main(String args[]) {
        set_1 = new HashSet<>();
        set_89 = new HashSet<>();
        family_stack = new Stack<>();
        for(int i = 1; i < 10000000; ++i){
            getFamily(i);
        }
        System.out.println(res);
    }
    
    static void getFamily(int n){
        while(true){
            int digit_sum = 0;
            int identity = 0;
            while(n > 0){
                int d = n%10;
                n/=10;
                identity += (1 << ((d<<1)+ d));
                digit_sum += d*d;
            }
            if(digit_sum == 1 || set_1.contains(identity)){
                while(!family_stack.empty()){
                    set_1.add(family_stack.pop());
                }
                break;
            }
            if(digit_sum == 89 || set_89.contains(identity)){
                while(!family_stack.empty()){
                    set_89.add(family_stack.pop());
                }
                ++res;
                break;
            }
            family_stack.push(identity);
            n = digit_sum;
        }
    }
}


