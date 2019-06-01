/*
  Problem at : http://codeforces.com/contest/633/problem/A
*/

import java.io.*;

public class MyClass {
    static int x;
    static int y;
    static int d;
    
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] vals = br.readLine().split(" ");
        int a = Integer.parseInt(vals[0]);
        int b = Integer.parseInt(vals[1]);
        int c = Integer.parseInt(vals[2]);
       
        int d = eed(a, b);
        if((c >= a || c >= b) && (c%d) == 0){
            int l = 0;
            int u = 0;
            if(x >= 0)
                l = -(x*c*d) /b;
            else
                l = (-x*c*d + b - 1)/b;
            if(y >= 0)
                u = (y*c*d) /a;
            else
                u = -(-y*c*d + a - 1)/a;    
            if(u >= l){
                System.out.println("Yes");
            }
            else 
                System.out.println("No");
        }
        else
            System.out.println("No");
       
    }
    
    static int eed(int a, int b){
        if(b == 0){
            x = 1;
            y = 0;
            return a;
        }
        
        d = eed(b, a%b);
        int temp = x;
        x = y;
        y = temp - (a/b)*y;
        return d;
    }
}


