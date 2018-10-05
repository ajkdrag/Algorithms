/*
    
    Problem at : https://projecteuler.net/problem=40

*/

public class Problem40 {
    public static void main(String args[]) {
        int start = 1;
        int res = 1;
        for(int i = 0; i < 7; ++i){
            res *= Solution40.getVal(start);
            start*=10;
        }
        System.out.println(res);
    }
}

class Solution40 {
    // arr[i] represents the count of the numbers with number of digits 'i'
    // static int[] arr = {0, 9, 180, 2700, 36000, 450000, 5400000, 63000000, 720000000};
    // pref array stores the prefix/cumulative sums
    static int[] pref = {0, 9, 189, 2889, 38889, 488889, 5888889, 68888889, 788888889};
    
    static int getVal (int n){
        int slot = getSlot(n);
        int x = n - pref[slot-1];
        int offset = (x + (slot - 1))/slot;
        int num = ((int)Math.pow(10,slot - 1) - 1) + offset;    
        int place = (x - 1)%slot;
        int digit = getDigit(num, slot, place);
        return digit;
    }   
    
    static int getSlot(int n){
        int i = 0;
        while(n > pref[i]){
            i++;
        }
        return i;
    }
    
    static int getDigit(int num, int total, int place){
        int temp = num/(int)(Math.pow(10,total - 1 - place));
        return temp%10;
    }
}

