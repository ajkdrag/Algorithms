/*
  Problem at : https://codeforces.com/problemset/problem/580/B
*/

import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scan sc = new Scan();
        int n = sc.scanInt();
        Sol sol = new Sol(n, sc.scanInt());
        for(int i = 0; i < n; ++i){
            sol.add(i, sc.scanInt(), sc.scanInt());
        }
        sol.solve();
    }
}
 
class Sol {
    int[] arr,arr2;
    Integer[] id;
    int n, limit;
    Sol(int n, int a){
        this.n = n;
        limit = a;
        arr = new int[n];
        arr2 = new int[n];
        id = new Integer[n];
    }
    
    void add(int i, int val1, int val2){
        arr[i] = val1;
        arr2[i] = val2;
        id[i] = i;
    }
    
    void solve(){
        Arrays.sort(id, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                Integer f1 = arr[a];
                Integer f2 = arr[b];
                return f1.compareTo(f2);
            }
        });
        int l = 0;
        int r = 0;
        long best = 0;
        long currSum = 0;
        while(r < n){
            currSum +=  arr2[id[r]];
            if(arr[id[r]] - arr[id[l]] >= limit){
                long temp = currSum - (r > l ? arr2[id[r]] : currSum);
                best = Math.max(best, temp);
                while(arr[id[r]] - arr[id[l]] >= limit){
                    currSum -= arr2[id[l]];
                    ++l;  
                }
            }
            ++r;
        }
        best = Math.max(best, currSum);
        System.out.println(best);
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


