/*
  Problem at : https://codeforces.com/contest/906/problem/A
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Sol sol = new Sol();
        for(int i = 1; i < n; ++i){
            String[] line = br.readLine().split(" ");
            char type = line[0].charAt(0);
            if(type == '!')
                sol.wrongWord(line[1]);
            else if(type == '.')
                sol.rightWord(line[1]);
            else
                sol.wrongGuess(line[1].charAt(0));
        }
        System.out.println(sol.shock - sol.mand);
    }
}
 
class Sol {
    Set<Character> set;
    int shock;
    int mand;
    
    Sol(){
        set = new HashSet<Character>();
        for(int i = 'a'; i <= 'z'; ++i){
            set.add((char)i);
        }
    }
    
    void wrongWord(String word){
        if(set.size() == 1){
            ++shock;
            return;
        }
        ++mand;
        ++shock;
        Set<Character> temp = new HashSet<Character>();
        for(char c : word.toCharArray()){
            if(set.contains(c))
                temp.add(c);
        }
        set = temp;
    }
    
    void wrongGuess(char guess){
        if(set.size() == 1){
            ++shock;
            return;
        }
        ++mand;
        ++shock;
        set.remove(guess);    
    }
    
    void rightWord(String word){
        if(set.size() == 1)
            return;
        for(char c : word.toCharArray()){
            set.remove(c);
        }
    }
}


