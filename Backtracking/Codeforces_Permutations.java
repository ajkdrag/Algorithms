/*
  Problem at : https://codeforces.com/problemset/problem/124/B
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Scan sc = new Scan();
        int n = sc.scanInt();
        int k = sc.scanInt();
        Sol sol = new Sol(n, k);
        for(int i = 0; i < n; ++i){
            sol.add(i, sc.scanString());
        }
        sol.dfs(0, 0);
        System.out.println(sol.res);
    }
}

class Sol {
    int n, k, res;
    char[][] nums;
    
    Sol(int n, int k){
        res = Integer.MAX_VALUE;
        this.n = n;
        this.k = k;
        nums = new char[n][k];
    }
    
    void add(int i, String num){
        nums[i] = num.toCharArray();
    }
    
    void dfs(int depth, int start){
        if(depth == k){
            compute();
        }
        
        for(int i = start; i < k; ++i){
            swap(i, start);
            dfs(depth+1, start+1);
            swap(i, start);
        }
    }
    
    void compute(){
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(char[] num : nums){
            int val = 0;
            for(char dig : num)
                val = (val*10 + (dig-'0'));
            if(val < min)
                min = val;
            if(val > max)
                max = val;
        }
        int diff = max- min;
        res = diff < res ? diff : res;
    }
    
    void swap(int i , int j){
        for(char[] num : nums){
            char t = num[i];
            num[i] = num[j];
            num[j] = t;
        }
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


