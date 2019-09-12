/*
  Problem at : https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=82
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = null;
        Sol sol = new Sol();
        while(!(a = br.readLine()).equals("#")){
            sol.solve(a);
        }
    }
}

class Sol {
   void solve(String a){
        char[] carr = a.toCharArray();
        int i = carr.length-2;
        int j = i+1;
        while(i >= 0 && carr[i] >= carr[i+1])
            --i;
        if(i < 0){
            System.out.println("No Successor");
            return;
        }
        for(; j > i; --j){
            if(carr[j] > carr[i])
                break;
        }
        char t = carr[j];
        carr[j] = carr[i];
        carr[i] = t;
        i++;
        j = carr.length - 1;
        while(i < j){
            t = carr[i];
            carr[i] = carr[j];
            carr[j] = t;
            ++i;
            --j;
        }
        System.out.println(new String(carr));
   }
}

