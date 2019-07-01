/*
  Problem at : https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=16&page=show_problem&problem=2287
*/

// using extended euclidean algorithm
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        Solution sol = new Solution();
        int t = sc.scanInt();
        while(t-- > 0){
            pt.println(sol.solve(sc.scanInt() ,sc.scanInt() ,sc.scanInt() ,sc.scanInt()));
        }
        pt.close();
    }
}

class Solution {
    long x=0,y=0,d=0;
    
    void egcd(long a, long b){
        if(b == 0){
            x = 1;
            y = 0;
            d = a;
            return;
        }
        
        egcd(b, a%b);
        long t = x;
        x = y;
        y = t - (a/b)*y;
    }
    
    String solve(long n, long l, long r, long t){
        egcd(r, l);
        y *= -1;
        t -= 1;
        if((t%d) != 0)
            return "uh-oh!";
        x *= t;
        y *= t;
        
        long k = 0;
        if(x < 0){
            k = -(-x + l - 1)/l;
        }
        else {
            k = x/l;
        }
        
        long k2 = 0;
        if(y < 0){
            k2 = -(-y + r - 1)/r;
        }
        else {
            k2 = y/r;
        }
        
        k = (k < k2 ? k : k2);
        
        long new_x = x/d - (l/d)*k;
        long new_y = y/d - (r/d)*k;
        long res = new_x + new_y;
        
        // simulate
        t += 1;
        long start = 1;
        while(new_x >= 0 && new_y >= 0){
            long temp = (n-start)/r;
            new_x -= temp;
            if(temp == 0 && new_x > 0)
                return "uh-oh!";
            start += (temp*r);
            if(start == t)
                break;
            temp = (start-1)/l;
            new_y -= temp;
            if(temp == 0 && new_y > 0)
                return "uh-oh!";;
            start -= (temp*l);
        }
        return Long.toString(res);
    }
}

// fast I/O
class Scan
{
    private byte[] buf=new byte[1024];    //Buffer of Bytes
    private int index;
    private InputStream in;
    private int total;
    public Scan()
    {
        in=System.in;
    }
    public int scan()throws IOException    //Scan method used to scan buf
    {
        if(index>=total)
        {
            index=0;
            total=in.read(buf);
            if(total<=0)
            return -1;
        }
        return buf[index++];
    }
    public int scanInt()throws IOException
    {
        int big=0;
        int n=scan();
        while(isWhiteSpace(n))    //Removing starting whitespaces
        n=scan();
        int neg=1;
        if(n=='-')                //If Negative Sign encounters
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n))
        {
            if(n>='0'&&n<='9')
            {
                big*=10;
                big+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*big;
    }
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
}

class Print
{
    private final BufferedWriter bw;
    public Print()
    {
        this.bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public void print(Object object)throws IOException
    {
        bw.append(""+object);
    }
    public void println(Object object)throws IOException
    {
        print(object);
        bw.append("\n");
    }
    public void close()throws IOException
    {
        bw.close();
    }
}


