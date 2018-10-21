/*

  Problem at : https://projecteuler.net/problem=43

*/

public class Problem43 {
    public static void main(String args[]) {
        System.out.println(Solution43.solve());
    }
}

class Solution43 {
    static int[] arr = new int[10];
    static int a,b,c,d,e,f,g,h,i,j;
    static long res;
    static long solve(){
        for(j = 7; j <= 9; j+=2){
            if(j == 7){
                i = 6;
                h = 8;
                g = 2;
                f = 5;
                e = 9;
                d = 0;
                c = 3;
                a = 1;
                b = 4;
                addToRes();
                a = 4;
                b = 1;
                addToRes();
            }
            else if(j == 9){
                i = 8;
                h = 2;
                g = 7;
                f = 5;
                e = 3;
                for(d = 0; d<= 6; d+=6){
                    c = 0;
                    if(d == 0){
                        c = 6;
                    }
                    a = 4;
                    b = 1;
                    addToRes();
                    a = 1;
                    b = 4;
                    addToRes();
                }
            }
        }   
        return res;
    }
    
    static void addToRes(){
        long temp = (a*1000000 + b*100000 + c*10000 + d*1000 + e*100 + f*10 + g);
        temp = temp*1000 + (h*100 + i*10 + j);
        res += temp;
    }
}


