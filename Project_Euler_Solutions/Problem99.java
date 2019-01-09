/*

  Problem at : https://projecteuler.net/problem=99
  
*/

import java.io.*;

public class Problem99 {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        String[] temp = new String[2];
        int max_line = 0;
        double max = 1;
        double curr = 0.0;
        int line_count = 0;
        while((s = br.readLine()) != null){
            ++line_count;            
            temp = s.split(",");
            int base = Integer.parseInt(temp[0]);
            int exp = Integer.parseInt(temp[1]);
            if((curr = exp * Math.log(base)) > max){
                max = curr;
                max_line = line_count;
            }
        }
        System.out.println(max_line);
    }
}

