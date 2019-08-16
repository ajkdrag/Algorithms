/*
  Problem at : https://codeforces.com/problemset/problem/930/A
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Solution sol = new Solution(n);
        String[] line = br.readLine().split(" ");
        for(int i = 2; i <= n; ++i){
            sol.ae(Integer.parseInt(line[i-2]), i);
        }
        sol.run();
    }
}
 
class Solution {
    List<Integer>[] al;
    
    Solution (int N){
        al = new ArrayList[N+1];
        for(int i = 1; i <= N; ++i)
            al[i] = new ArrayList<Integer>();
    }
    void ae(int a, int b){
        al[a].add(b);        
    }
    
    // used bfs here
    void run(){
        Queue<Integer> q = new LinkedList<Integer>();
        int res = 1;
        q.offer(1);
        int sz = 1;
        while(!q.isEmpty()){
            int curr = q.poll();
            --sz;
            for(int nbr : al[curr]){
                q.offer(nbr);
            }
            if(sz == 0){
                sz = q.size();
                res += (sz%2);
            }
        }
        System.out.println(res);
    }
}

