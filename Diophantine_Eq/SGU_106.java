/*
  Problem at : http://codeforces.com/problemsets/acmsguru/problem/99999/106
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while((line = br.readLine()) != null){
            String[] temp = line.split(" ");
            long a = Long.parseLong(temp[0]);
            long b = Long.parseLong(temp[1]);
            long c = -1*Long.parseLong(temp[2]);
            
            line = br.readLine();
            temp = line.split(" ");
            long min_x = Long.parseLong(temp[0]);
            long max_x = Long.parseLong(temp[1]);
            
            line = br.readLine();
            temp = line.split(" ");
            long min_y = Long.parseLong(temp[0]);
            long max_y = Long.parseLong(temp[1]);
            
            Solution sol = new Solution();
            System.out.println(sol.solve(a, b, c, min_x, max_x, min_y, max_y));
        }
    }
}

class Solution {
    long x, y, g = 0;
    
    void shift(long a, long b, long k){
        x += k*(b/g);
        y -= k*(a/g);
    }
    
    void unshift(long a, long b, long k){
        x -= k*(b/g);
        y += k*(a/g);
    }
    
    long solve(long a, long b, long c, long x1, long x2, long y1, long y2){
        if(a == 0 && b == 0){
            if(c != 0)
                return 0;
            g = (x2 - x1 + 1)*(y2 - y1 + 1);
            return g;
            
        }
        if(a == 0){
            if((c%b)!=0)
                return 0;
            y = c/b;
            if(y1 <= y && y <= y2)
                g = x2 - x1 + 1;
            
            return g;
        }
        if(b == 0){
            if((c%a)!=0)
                return 0;
            x = c/a;
            if(x1 <= x && x <= x2)
                g = y2 - y1 + 1;
            
            return g;
        }
        long sign_a = a < 0 ? -1 : 1;
        long sign_b = b < 0 ? -1 : 1;
        
        eed(a*sign_a, b*sign_b);
        if((c%g)!=0)
                return 0;
        x *= sign_a * (c / g);
        y *= sign_b * (c / g);
        
        long k = -1;
        long l_x1 = -1;
        long u_x1 = -1;
        long l_x2 = -1;
        long u_x2 = -1;
        long num = -1;
        long den = -1;
        
        l_x1 = -1;
        u_x1 = -1;
        
        if(sign_b == 1){
            num = x1 - x;
            if(num >= 0)
                l_x1 = (num + (b/g) - 1)/(b/g);
            else
                l_x1 = -(-num)/(b/g);
            
            num = x2 - x;
            if(num > 0)
                u_x1 = (num)/(b/g);
            else
                u_x1 = -(-num + (b/g) - 1)/(b/g);
        }
        else{
            num = x2 - x;
            if(num > 0)
                l_x1 = -(num)/(-b/g);
            else
                l_x1 = (-num + (-b/g) - 1)/(-b/g);
            num = x1 - x;
            if(num >= 0)
                u_x1 = -(num + (-b/g) - 1)/(-b/g);
            else
                u_x1 = (-num)/(-b/g);
        }    
            
        l_x2 = -1;
        u_x2 = -1;
        if(sign_a == 1){
            num = y - y1;
            if(num > 0)
                u_x2 = (num)/(a/g);
            else
                u_x2 = -(-num + (a/g) - 1)/(a/g);
            num = y - y2;
            if(num >= 0)
                l_x2 = (num + (a/g) - 1)/(a/g);
            else
                l_x2 = -(-num)/(a/g);
        }
        else {
            num = y - y1;
            if(num > 0)
                l_x2 = -(num)/(-a/g);
            else
                l_x2 = (-num + (-a/g) - 1)/(-a/g);
            
            num = y - y2;
            if(num >= 0)
                u_x2 = -(num + (-a/g) - 1)/(-a/g);
            else
                u_x2 = (-num)/(-a/g);
            
        }
        
        if(l_x2 < l_x1){
            long t = l_x2;
            l_x2 = l_x1;
            l_x1 = t;
            t = u_x2;
            u_x2 = u_x1;
            u_x1 = t;
        }
        
        if(!(l_x1 <= u_x2 && l_x2 <= u_x1))
            return 0;
        
        long low = Math.max(l_x1, l_x2);
        long hi = Math.min(u_x1, u_x2);
        return hi - low + 1;
    }
    
    
    void eed(long a, long b){
        if(b == 0){
            x = 1;
            y = 0;
            g = a;
            return;
        }
        eed(b, a%b);
        long t = x;
        x = y;
        y = t - (a/b)*y;
    }
}


