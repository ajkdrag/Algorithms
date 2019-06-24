/*
  Problem at : https://www.spoj.com/problems/NAKANJ/
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int t = Integer.parseInt(br.readLine());
    	Solution sol = new Solution();
    	while(t-- > 0){
    		String ip = br.readLine();
    		System.out.println(sol.solve(ip.substring(0,2),ip.substring(3,5)));
    	}
    }
}

class Solution {
    int[] visited;
    int[] rMask = {-1, -1, -2, -2, 1, 1, 2, 2};
    int[] cMask = {-2, 2, -1, 1, 2, -2, 1, -1};
    Queue<Integer> queue;
    
    int solve(String src, String dest){
        return bfs(parse(src), parse(dest));
    }
    
    int bfs(int src, int dest){
    	int numSteps = 0;
    	if(src == dest)
    		return numSteps;
    	queue = new LinkedList<Integer>();
    	visited = new int[64];
    	queue.add(src);
    	visited[src] = 1;
    	numSteps = 0;
    	int sz = 1;
    	boolean updated = false;
    	while(!queue.isEmpty()){
    		if(sz > 0 && !updated){
    			++numSteps;
    			updated = true;
    		}
    		int currPos = queue.remove();
    		sz--;
    		visited[currPos] = 1;
			int[] nextPos = nextMoves(currPos);  
			for(int pos : nextPos){
				if(pos < 0 || visited[pos] == 1)
					continue;
				if(pos == dest)
					return numSteps;
				queue.add(pos);
			}
			if(sz == 0){
    			sz = queue.size();
    			updated = false;
			}
    	}
    	return numSteps;
    }
    
    int[] nextMoves(int currPos){
    	int row = currPos>>3;
    	int col = currPos&7;
    	int[] nextPos = new int[8];
    	for(int i = 0; i < 8; ++i){
    		nextPos[i] = -1;
    		int newR = row + rMask[i];
    		int newC = col + cMask[i];
    		if(newR < 0 || newC < 0 || newR > 7 || newC > 7)
    			continue;
    		nextPos[i] = (newR<<3) + newC;
    	}
    	return nextPos;
    }
    
    int parse(String s){
        int col = s.charAt(0) - 'a';
        int row = '8' - s.charAt(1);
        return (row<<3) + col;
    }
}


