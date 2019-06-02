/*
  Problem at : http://www.lightoj.com/volume_showproblem.php?problem=1306
*/

import java.io.*;

public class MyClass {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Solution sol = new Solution();
        int t = Integer.parseInt(line);
        for(int i = 1; i <= t; ++i){
            String[] ip = br.readLine().split(" ");
            System.out.println("Case " + i + ": " + sol.solve(Long.parseLong(ip[0]),Long.parseLong(ip[1]),-Long.parseLong(ip[2]),Long.parseLong(ip[3]),
                                                        Long.parseLong(ip[4]),Long.parseLong(ip[5]),Long.parseLong(ip[6])));
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
        // System.out.println(l_x1);
        // System.out.println(u_x1);
        // System.out.println(l_x2);
        // System.out.println(u_x2);
        
        // System.out.println(x + " " + y );
        
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


