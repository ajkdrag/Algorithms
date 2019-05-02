/*
  Problem at : https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1045
  Straightforward Extended Euclidean Algorithm application.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int x = 0;
    static int y = 0;
    static int d = 0;
    static String[] vals;
    
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while((line = br.readLine()) != null){
            vals = line.split(" ");
            eed(Integer.parseInt(vals[0]), Integer.parseInt(vals[1]));
            System.out.println(x + " "+  y + " " + d);
        }
    }
    
    static void eed(int a, int b){
        if(b == 0){
            x = 1;
            y = 0;
            d = a;
            return;
        }
        
        eed(b, a%b);
        int temp = x;
        x = y;
        y = temp - (a/b)*y;
    }
}


