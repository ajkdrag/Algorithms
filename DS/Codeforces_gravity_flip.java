/*
  Problem at : https://codeforces.com/contest/405/problem/A
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).sorted().forEach(x -> System.out.print(x + " "));
    }
}

