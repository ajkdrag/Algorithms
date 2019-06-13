/*
  Problem at : https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2338
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        sol.fillSieve();
        Scan sc = new Scan();
        Print pt = new Print();
        int x = -1;
        int t = 0;
        while((x = sc.scanInt()) != 0){
            ++t;
            pt.println("Case " + t + ": " + sol.arr[x]);
        }
        pt.close();
    }    
}

class Solution {
    int limit = 2000000;
    int[] sieve;
    Integer[] arr;
    
    void fillSieve(){
        sieve = new int[limit + 1];
        arr = new Integer[limit + 1];
        int ct = -1;
        int cp = -1;
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 2; i <= limit; ++i){
            arr[i] = i;
            if(sieve[i] == 0){
                sieve[i] = 1;
                for(int j = i<<1; j <= limit; j+=i){
                    ct = 0;
                    cp = j;
                    while((cp%i)==0){
                        cp/=i;
                        ++ct;
                    }
                    sieve[j] += ct;
                }
            }
        }
        Arrays.sort(arr, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                int s = sieve[a] - sieve[b];
                if(s < 0)
                    return -1;
                if(s > 0)
                    return 1;
                return 0;
            }
            
        });
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


