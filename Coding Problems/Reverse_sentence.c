/*
  Given an input string as such "Rahul is a boy", output should be "boy a is Rahul"
  Note that reversal needs to be inplace.
*/

#include<stdio.h>
#include<string.h>

void reverse(char* arr, int i, int j);

int main() {
    char arr[100];
    int i,j = 0;
    fgets(arr,100,stdin);
    int len = strlen(arr);
    if(arr[len-1] == '\n'){
        arr[len-1] = '\0';
        --len;
    }
    reverse(arr,0,len-1);
    arr[len] = ' ';
    
    for( ; j <= len; ++j){
        if(arr[j] == ' '){
            reverse(arr,i,j-1);
            i = j+1;
        }
    }
    puts(arr);
    return 0;
}

void reverse(char* arr, int i, int j){
    char temp = '0';
    while(i <= j){
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        i++;
        j--;
    }
}

/* Java version :

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Reverse_sentence {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Solution.reverse(br.readLine()));
    }
}

class Solution {
    static String reverse(String in){
        String[] arr = in.split(" ");
        int i = 0;
        int j = arr.length - 1;
        while(i <= j){
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        StringBuilder sb = new StringBuilder();
        for(String s : arr){
            sb.append(s);
            sb.append(" ");
        } 
        return sb.toString();
    }
}
*/


