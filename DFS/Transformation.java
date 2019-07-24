/*
  Problem at : http://codeforces.com/contest/727/problem/A
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Solution sol = new Solution(sc.scanInt(), sc.scanInt());
    }
}
 
class Solution {
    StringBuilder res = new StringBuilder();
    int len = 0;
    int start;
    boolean dfs(int curr){
        if(curr < start)
            return false;
        boolean op = false;
        int last = curr%10;
        if(curr == start)
            op = true;
        else if((curr&1)==0)
            op = dfs(curr>>1);
        else if(last == 1)
            op = dfs(curr/10);
       
        if(op){
            if(curr != start)
                res.append(" ");
            res.append(curr);
            len += 1;   
        }
        return op;
    }
    
    Solution(int start, int end) throws IOException{
        Print pt = new Print();
        this.start = start;
        boolean result = dfs(end);
        if(!result)
            pt.println("NO");
        else
            {
                pt.println("YES");
                pt.println(len);
                pt.println(res.toString());
            }
        pt.close();
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

