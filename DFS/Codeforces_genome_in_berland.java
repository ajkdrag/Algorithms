/*
  Problem at : http://codeforces.com/contest/638/problem/B
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Sol sol = new Sol(n);
        while(n-- > 0){
            String s = br.readLine();
            sol.add(s);
        }
        sol.solve();
    }
}
 
class Sol {
    ArrayList<Integer>[] al;
    char[] carr;
    int[] v;
    String res = "";
    
    Sol(int n){
        al = new ArrayList[26];
        v = new int[26];
        for(int i = 0; i < 26; ++i)
            al[i] = new ArrayList<Integer>(26);
    }
    
    void go(int sr){
        v[sr]=0;
        System.out.print((char)(sr+'a'));
        for(int i : al[sr]){
            if(v[i] > 0)
                go(i);
        }
    }
    
    void solve(){
        for(int i = 0; i < 26; ++i){
            if(v[i] == 1)
                go(i);
        }
    }
    
    void add(String s){
        carr = s.toCharArray();    
        for(int i = 0, j = carr.length; i < j-1; ++i){
            al[carr[i]-'a'].add(carr[i+1]-'a');
            v[carr[i+1]-'a'] = 2;
        }
       if(v[carr[0]-'a'] != 2)
            v[carr[0]-'a'] = 1;
    }
}


