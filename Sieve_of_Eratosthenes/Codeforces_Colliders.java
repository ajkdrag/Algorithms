/*
  Problem at : https://codeforces.com/problemset/problem/154/B
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution sol = new Solution();
        Print pt = new Print();
        String line = br.readLine();
        while((line = br.readLine()) != null){
            String[] ip = line.split(" ");
            if(ip[0].equals("+"))
                pt.println(sol.insert(Integer.parseInt(ip[1])));
            else
                pt.println(sol.remove(Integer.parseInt(ip[1])));
        }
        pt.close();
    }
}

class Solution {
    HashSet<Integer> inserted;
    
    int comp_gcd(int a, int b){
        if(b == 0)
            return a;
        return comp_gcd(b, a%b);
    }
    
    
    String insert(int a){
        if(inserted.contains(a))
            return "Already on";
        
        for(int x : inserted){
            if(comp_gcd(x, a) != 1)
                return "Conflict with " + x;
        }
        inserted.add(a);
        return "Success";
    }
    
    String remove(int a){
        if(inserted.contains(a)){
            inserted.remove(a);
            return "Success";
        }
        return "Already off";
    }
    
    Solution() {
        inserted = new HashSet<Integer>();
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


