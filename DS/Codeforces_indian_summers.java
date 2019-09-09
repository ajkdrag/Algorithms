/*
  Problem at : https://codeforces.com/contest/44/problem/A
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashSet<String> set = new HashSet<String>();
        String str = "";
        int ct = 0;
        while(n-- > 0){
            if(!set.contains(str = br.readLine())){
                ++ct;
                set.add(str);
            }
        }
        System.out.println(ct);
    }
}

