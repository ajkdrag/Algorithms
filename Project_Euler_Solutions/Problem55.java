/*

  Problem at : https://projecteuler.net/problem=55

*/

// Recursive approach with some caching

import java.math.BigInteger;

public class Problem55 {
    public static void main(String args[]) {
        System.out.print(Solution55.solve());
    }
}

class Solution55 {
    static int[] isL;
    
    static int solve(){
        isL = new int[10000];
        BigInteger bi;
        int count = 0;
        for(int i = 10; i < 10000; ++i){
            if(isL[i] == -1) continue;
            bi = BigInteger.valueOf(i);
            if(isLych_num(bi,1)) ++count;
        }
        return count;
    }
    
    static boolean isLych_num(BigInteger num, int iter){
        if(iter > 50) return true;
        BigInteger rev = reverse(num);
        BigInteger toCheck = num.add(rev);
        if(toCheck.equals(reverse(toCheck)) || !isLych_num(toCheck,++iter)){
            if(len(num) <= 4) isL[num.intValue()] = -1;
            if(len(rev) <= 4) isL[rev.intValue()] = -1;
            return false;
        }
        return true;
    }
    
    static int len(BigInteger number) {
        double factor = Math.log10(2);
        int digitCount = (int) (factor * number.bitLength() + 1);
        if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
            return digitCount - 1;
        }
        return digitCount;
    }
    
    static BigInteger reverse(BigInteger num){
        StringBuilder sb = new StringBuilder(num.toString());
        sb = sb.reverse();
        return new BigInteger(sb.toString());
    }
}

// Brute-force with no caching (runs faster than above)

import java.math.BigInteger;

public class Main {
    public static void main(String abcd[]) {
        int d = 0;
        x: for (int b = 1; b < 10000; b++) {
            BigInteger a = BigInteger.valueOf(b);
            for (int c = 1; c <= 50; c++) {
                a = a.add(rev(a));
                if (a.equals(rev(a))) {
                    continue x;
                }
            }
            d++;
        }
        System.out.println(d);
    }
    static BigInteger rev(BigInteger a) {
        StringBuilder sb = new StringBuilder(a.toString());
        return new BigInteger(sb.reverse().toString());
    }
}


// Iterative solution with even more caching (faster than above)

import java.math.BigInteger;
import java.util.Stack;
import java.util.HashSet;

public class Problem55 {
    public static void main(String args[]) {
        System.out.print(Solution55.solve());
    }
}

class Solution55 {
    static BigInteger[] startVal;
    static int[] isL;
    static Stack<Integer> st;
    static HashSet<BigInteger> map;
    static int solve(){
        int count = 0;
        st = new Stack<Integer>();
        map = new HashSet<BigInteger>();
        isL = new int[10000];
        startVal = new BigInteger[10000];
        
        for(int i = 9999; i >= 1; --i){
            startVal[i] = BigInteger.valueOf(i);
        }
        
        BigInteger bi;
        boolean isLych = true;
        
        for(int i = 10; i < 10000; ++i){
            isLych = true;
            int start = isL[i];
            if(start == -1){
                continue;
            }
            bi = startVal[i];
            if(map.contains(bi)){
                ++count;
                continue;
            }
            
            int counter = 0;
            for(; start <= 50; ++start){
                BigInteger rev = reverse(bi);
                if(bi.equals(rev) && start > 0){
                    isLych = false;
                    break;
                }
                if(len(bi) <= 4) {
                    int val1 = bi.intValue();
                    st.push(val1);
                    isL[val1] = counter;
                }
                if(len(rev) <= 4) {
                    int val2 = rev.intValue();
                    if(val2 > i) {
                        st.push(val2);
                        isL[val2] = counter;
                    }
                }
                bi = bi.add(rev);
                counter++;
            } 
            
            if(!isLych){
                while(!st.isEmpty()){
                    isL[st.pop()] = -1;
                }
            }
            else {
                while(!st.isEmpty()){
                    startVal[st.pop()] = bi;
                }
                map.add(startVal[i]);
                ++count;
            }
        }
        return count;
    }
    
    static int len(BigInteger number) {
        double factor = Math.log10(2);
        int digitCount = (int) (factor * number.bitLength() + 1);
        if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
            return digitCount - 1;
        }
        return digitCount;
    }
    
    static BigInteger reverse(BigInteger num){
        StringBuilder sb = new StringBuilder(num.toString());
        sb = sb.reverse();
        return new BigInteger(sb.toString());
    }
}


