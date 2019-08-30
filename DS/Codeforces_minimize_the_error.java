/*
  Problem at : https://codeforces.com/contest/960/problem/B
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main (String[] args) throws IOException {
        Scan sc = new Scan();
        int n = sc.scanInt();
        int k1 = sc.scanInt();
        int k2 = sc.scanInt();
        Sol sol = new Sol(n, k1+k2);
        for(int i = 0; i < n; ++i){
            sol.arr[i] = sc.scanInt();
        }
        for(int i = 0; i < n; ++i){
            sol.arr[i] -= sc.scanInt();
        }
        System.out.println(sol.solve());
    }
}
 
class Sol {
    int[] arr;
    int n, k;
    Sol(int n , int k){
        this.n = n;
        this.k = k;
        arr = new int[n];
    }
    
    long solve(){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n, new Comparator<Integer>() {
            public int compare(Integer n1, Integer n2) {
                if(Math.abs(arr[n1]) < Math.abs(arr[n2])){
                    return 1;
                }
                else
                    return -1;
            }
        });
        
        for(int i = 0; i < n; ++i)
            pq.add(i);
        long res = 0;
        for(int i = 1; i <= k; ++i){
            int best = pq.poll();
            int val = arr[best];
            if(val > 0)
                arr[best]-=1;
            else arr[best]+=1;
            pq.add(best);
        }
        for(int i : arr){
            res += ((long)i*(long)i);            
        }
        return res;
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


