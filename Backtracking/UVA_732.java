/*
  Problem at : https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=673
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = null;
        String b = null;
        Sol sol = new Sol();
        while((a = br.readLine()) != null){
            b = br.readLine();
            sol.solve(a, b);
        }
    }
}

class Sol {
    char[] st;
    char[] ip;
    char[] to;
    
    void dfs(int from, String path, int curr, int stTop){
        if(curr == to.length){
            System.out.println(path.substring(0,path.length()-1));
            return;
        }
        
        if(from < ip.length){
            char temp = st[stTop+1];
            st[stTop+1] = ip[from];
            dfs(from+1, path + "i ", curr, stTop+1);
            st[stTop+1] = temp;
        }
        
        if(stTop >= 0){
            char top = st[stTop];
            if(top == to[curr]){
                dfs(from, path + "o ", curr+1, stTop-1);
            }
        }
    }
    
    void solve(String a, String b){
        ip = a.toCharArray();
        to = b.toCharArray();
        st = new char[ip.length+1];
        System.out.println("[");
        if(ip.length == to.length)
            dfs(0, "", 0, -1);
        System.out.println("]");
    }
}

