/*
  Problem at : https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2387
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Print pt = new Print();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while((line = br.readLine())!=null){
            int n = Integer.parseInt(line);
            Solution sol = new Solution(n);
            pt.println(sol.bfs());
        }
        pt.close();
    }
}

class Solution {
    int[] rmds;
    int n;
    Queue<Node> queue;
    
    Solution(int n){
        this.n = n;
    }
    
    String bfs(){
        // trivial cases
        if(n == 0)
            return "1 0 1";
        if(n == 1 || n == 3)
            return "1 1 0";
                
        queue = new LinkedList<Node>();
        queue.offer(new Node(1, 0, 3%n));
        rmds = new int[n];
        
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            
            int newMod1 = (curr.mod*10)%n;
            if(newMod1 == 0){
                int num3s = curr.num3;
                int num0s = curr.num0 + 1;
                int total = num3s + num0s;
                return total + " " + num3s + " " + num0s;
            }
            if(curr.num0 == 0){
                int newMod = (newMod1 + 3)%n;
                if(newMod == 0){
                    int num3s = curr.num3 + 1;
                    int num0s = curr.num0;
                    int total = num3s + num0s;
                    return total + " " + num3s + " " + num0s;
                }
                queue.offer(new Node(curr.num3 + 1, curr.num0, newMod));
                   
            }
            if(rmds[newMod1] == 0){
                rmds[newMod1] = 1;
                queue.offer(new Node(curr.num3, curr.num0+1, newMod1));
            }
        }
        return "-1";
    }
}

class Node {
    int num3;
    int num0;
    int mod;
    
    Node(int num3, int num0, int mod){
        this.num3 = num3;
        this.num0 = num0;
        this.mod = mod;
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

