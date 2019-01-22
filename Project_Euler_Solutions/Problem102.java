/*
  Problem at : https://projecteuler.net/problem=102
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Problem102 {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int count = 0;
        while((line = br.readLine()) != null){
            int[] X = new int[3];
            int[] Y = new int[3];
            String[] temp = line.split(",");
            for(int i = 0; i < 6; ++i){
                if((i&1)==0)
                    X[i>>1] = Integer.parseInt(temp[i]);
                else
                    Y[i>>1] = Integer.parseInt(temp[i]);
            }
            if(contains(X, Y))
                ++count;
        }
        System.out.println(count);
    }
    
    static boolean contains(int[] X, int[] Y){
        double area = 0;
        for(int i = 0; i < 3; ++i){
            int[] tempX = new int[X.length];
            int[] tempY = new int[Y.length];
            System.arraycopy(X, 0, tempX, 0, X.length);
            System.arraycopy(Y, 0, tempY, 0, Y.length);
            tempX[i] = 0;
            tempY[i] = 0;
            area += getArea(tempX, tempY);
        }
        if(area == getArea(X, Y))
            return true;
        return false;
    }
    
    static double getArea(int[] X, int[] Y){
        double area = 0;     
        int j = 2; 
        for (int i=0; i < 3; i++){
            area = area + (X[j]+X[i]) * (Y[j]-Y[i]); 
            j = i; 
        }
        return Math.abs(area/2);
    }
}


