/*
  Problem at : https://codeforces.com/contest/909/problem/D
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Sol sol = new Sol();
        System.out.println(sol.init(br.readLine()));
    }
}
 
class Sol {
    int[] nxt,prev;
    char[] chars;
    int n;
    
    int init(String s){
        n = s.length();
        nxt = new int[n+2];
        prev = new int[n+2];
        chars = new char[n+2];
        nxt[0] = 1;
        nxt[n+1] = -1;
        prev[0] = -1;
        prev[n+1] = n-1;
        chars[0] = '#';
        chars[n+1] = '#';
        for(int i = 1; i < n + 1; ++i){
            nxt[i] = i+1;
            prev[i] = i-1;
            chars[i] = s.charAt(i-1);
        }
        int ct = 0;
        HashSet<Integer> potent = new HashSet<Integer>();
        boolean good = true;
        int p = 0;
        int c = nxt[0];
        int ne = nxt[c];
        while(c != n + 1){
            if(!cmp(p,c) || !cmp(c,ne)){
                nxt[prev[c]] = nxt[c];
                prev[nxt[c]] = prev[c];
                if(potent.contains(c)){
                    potent.remove(c);
                }
                if(prev[c] != 0)
                    potent.add(prev[c]);
                if(nxt[c] != n+1)
                    potent.add(nxt[c]);
                good = false;
            }
            p = c;
            c = nxt[c];
            ne = nxt[c];
        }
        if(good)
            return 0;
        if(!good && potent.size() == 0)
            return 1;
        while(potent.size() != 0){
            ++ct;
            HashSet<Integer> fnd = new HashSet<Integer>();
            for(int num : potent){
                p = prev[num];
                ne = nxt[num];
                if(!cmp(p,num) || !cmp(num,ne))
                    fnd.add(num);
            }
            potent.clear();
            for(int num : fnd){
                nxt[prev[num]] = nxt[num];
                prev[nxt[num]] = prev[num];
                if(potent.contains(num)){
                    potent.remove(num);
                }
                if(prev[num] != 0){
                    potent.add(prev[num]);
                }
                if(nxt[num] != n+1)
                    potent.add(nxt[num]);
            }
            if(fnd.size() > 0 && potent.size() == 0)
                ++ct;
        }
        return ct;
    }
    
    boolean cmp(int a, int b){
        if(a == 0 || b == 0 || a == n+1 || b == n+1)
            return true;
        int charA = chars[a];
        int charB = chars[b];
        return (charA == charB);
    }
}


