/*
  Problem at : https://codeforces.com/contest/501/problem/B
*/

import java.util.*;
import java.io.*;
 
public class Main {
    public static void  main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Sol sol = new Sol();
        while(n-- > 0){
            String[] tmp = br.readLine().split(" ");
            sol.add(tmp[0], tmp[1]);
        }
        sol.solve();
    }
}
 
class Sol {
    int[] arr;
    String[] arr2;
    HashMap<String, Integer> map;
    int cnt;
    
    Sol(){
        cnt = 0;
        arr = new int[2010];
        arr2 = new String[2010];
        map = new HashMap<String, Integer>();
    }
 
    void add(String a, String b){
        int id1 = -1;
        int id2 = -1;
        if(map.containsKey(a))
            id1 = map.get(a);
        else {
            ++cnt;
            id1 = cnt;
            map.put(a, id1);
            arr2[id1] = a;
        }
        
        if(map.containsKey(b))
            id1 = map.get(b);
        else {
            ++cnt;
            id2 = cnt;
            map.put(b, id2);
            arr2[id2] = b;
        }
        //System.out.println(id1 + ", " + id2);
        
        if(arr[id1] > 0){
            arr[id2] = arr[id1];
            arr[id1] = 0;
        }
        else {
            arr[id2] = id1;
        }
    }
    
    void solve(){
        int ct = 0;
        String res = "";
        for(int i = 1; i <= cnt; ++i){
            if(arr[i] > 0){
                ++ct;
                res += (arr2[arr[i]] + " " + arr2[i] + "\n");
            }
        }
        System.out.println(ct);
        System.out.print(res);
    }
}

