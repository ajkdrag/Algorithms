/*
  Problem at : https://www.spoj.com/problems/MULTII/
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Print pt = new Print();
        Scan sc = new Scan();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int i = 0;
        while((line = br.readLine())!=null){
            ++i;
            String[] ips = line.split(" ");
            int n = Integer.parseInt(ips[0]);
            int m = Integer.parseInt(ips[1]);
            
            Solution sol = new Solution(n);
            ips = br.readLine().split(" ");
            while(m-- > 0){
                sol.forb[Integer.parseInt(ips[m])] = 1;
            }
            pt.println("Case " + i + ": " + sol.bfs());
        }
        pt.close();
    }
}

class Solution {
    int[] rmds;
    int[] forb;
    int n;
    Queue<Node> queue;
    
    Solution(int n){
        forb = new int[10];  
        this.n = n;
    }
    
    String bfs(){
        // trivial cases
        if(n < 10)
            return forb[n] == 1 ? "-1" : n+"";
        
        int cpy = n;
        boolean isValid = true;
        while(cpy > 0){
            if(forb[cpy%10] == 1){
                isValid = false;
                break;
            }
            cpy/=10;
        }
        if(isValid)
            return ""+n;
        
        rmds = new int[n];
        // push starting digits
        queue = new LinkedList<Node>();
        for(int i = 1; i < 10; ++i){
            if(forb[i] == 1)
                continue;
            rmds[i] = 1;
            queue.offer(new Node(i+"", i));
        }
        
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            for(int i = 0; i < 10; ++i){
                if(forb[i] == 1)
                    continue;
                int newMod = (curr.mod*10 + i)%n;
                if(newMod == 0)
                    return curr.num+i;
                if(rmds[newMod] == 1)
                    continue;
                rmds[newMod] = 1;
                queue.offer(new Node(curr.num+i,newMod));
            }
        }
        return "-1";
    }
}

class Node {
    String num;
    int mod;
    
    Node(String num, int mod){
        this.num = num;
        this.mod = mod;
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


