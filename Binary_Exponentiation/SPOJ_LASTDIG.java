/*
  Problem at at : https://www.spoj.com/problems/LASTDIG/
  Basic application of binary exponentiation to find the last digit.
*/

import java.io.*;
public class Main {
    static String[] currLine;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0){
		    currLine = br.readLine().split(" ");
		    System.out.println(trailing(Integer.parseInt(currLine[0]), Integer.parseInt(currLine[1])));
		  }
    }
    static int trailing(int n, int k){
        n %= 10;
        int res = 1;
        while(k > 0){
            if((k&1) == 1)
                res = (res*n)%10;
            n = (n*n)%10;
            k >>= 1;
        }
        return res;
    }
}


