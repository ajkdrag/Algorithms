/*
  Problem at : https://codeforces.com/problemset/problem/121/A
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Scan sc = new Scan();
        int l = sc.scanInt();
        int r = sc.scanInt();
        new Sol(l, r);
    }
}

class Sol {
    int l, r;
    
    Sol(int l, int r){
        this.l = l;
        this.r = r;
        findLuckyNums();
    }
    
    void findLuckyNums(){
        Queue<Long> q = new LinkedList<Long>();
        q.offer(4L);
        q.offer(7L);
        long prefL = 0;
        long prefR = 0;
        long prev = 0;
        
        boolean foundR = false;
        boolean foundL = false;
        
        while(!q.isEmpty()){
            long curr = q.poll();
            if(!foundL && curr <= l-1){
                prefL += (curr-prev)*curr;
            }
            else if(!foundL && curr > l-1){
                prefL += (l-1-prev)*curr;
                foundL = true;
            }
            
            if(!foundR && curr <= r){
                prefR += (curr-prev)*curr;
            }
            else if(!foundR && curr > r){
                prefR += (r-prev)*curr;
                break;
            }
            
            q.offer(curr*10 + 4);
            q.offer(curr*10 + 7);
            prev = curr;
        }
        System.out.println(prefR - prefL);
    }
}

class Scan
{
    private byte[] buf=new byte[1024];
    private int index;
    private InputStream in;
    private int total;
    public Scan()
    {
        in=System.in;
    }
    public int scan()throws IOException
    {
        if(total<0)
        throw new InputMismatchException();
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
        int integer=0;
        int n=scan();
        while(isWhiteSpace(n))
        n=scan();
        int neg=1;
        if(n=='-')
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n))
        {
            if(n>='0'&&n<='9')
            {
                integer*=10;
                integer+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*integer;
    }
    public double scanDouble()throws IOException
    {
        double doub=0;
        int n=scan();
        while(isWhiteSpace(n))
        n=scan();
        int neg=1;
        if(n=='-')
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n)&&n!='.')
        {
            if(n>='0'&&n<='9')
            {
                doub*=10;
                doub+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        if(n=='.')
        {
            n=scan();
            double temp=1;
            while(!isWhiteSpace(n))
            {
                if(n>='0'&&n<='9')
                {
                    temp/=10;
                    doub+=(n-'0')*temp;
                    n=scan();
                }
                else throw new InputMismatchException();
            }
        }
        return doub*neg;
    }
    public String scanString()throws IOException
    {
        StringBuilder sb=new StringBuilder();
        int n=scan();
        while(isWhiteSpace(n))
        n=scan();
        while(!isWhiteSpace(n))
        {
            sb.append((char)n);
            n=scan();
        }
        return sb.toString();
    }
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
}


