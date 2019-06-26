/*
  Problem at : 
*/

import java.util.*;
import java.io.*;

class Halloween {
    public static void main(String args[]) throws IOException{
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        
        int t = in.readInt();
        for(int s = 1; s <= t; ++s){
            int r = in.readInt();
            int c = in.readInt();
            Solution sol = new Solution(r, c);
            for(int i= 0; i < r; ++i){
                for(int j = 0; j < c; ++j)
                    sol.fillGrid(i, j, in.readCharacter());
            }
            out.println("Case " + s + ": " + sol.bfs());
        }
        out.close();
    }
}

class Solution {
    char[][] grid;
    int[][] visited;
    int m;
    int n;
    int[] dirX = {-1, 0, 1 , 0};
    int[] dirY = { 0, -1, 0, 1};
    Deque<Integer> queue;
    
    Solution(int m, int n){
        this.m = m;
        this.n = n;
        grid = new char[m][n];
        visited = new int[m][n];
        for(int[] x : visited)
            Arrays.fill(x, Integer.MAX_VALUE - 10);
    }
    
    void fillGrid(int row, int col, char c){
        grid[row][col] = c;
    }
    
    int bfs(){
        queue = new LinkedList<Integer>();
        queue.add(0);
        visited[0][0] = 0;
        
        while(!queue.isEmpty()){
            int curr = queue.poll();
            int currX = curr/n;
            int currY = curr%n;
            
            for(int i = 0; i < 4; ++i){
                int newX = currX + dirX[i];
                int newY = currY + dirY[i];
                if(newX < 0 || newY < 0 || newX >= m || newY >= n)
                    continue;
                int currVal = visited[newX][newY];
                int newVal = visited[currX][currY] + (grid[currX][currY] == grid[newX][newY] ? 0 : 1);
                if(newVal < currVal){
                    visited[newX][newY] = newVal;
                    if(newVal == visited[currX][currY])
                        queue.addFirst(newX*n + newY);
                    else
                        queue.addLast(newX*n + newY);
                }
                if(newX == m - 1 && newY == n - 1 && newVal == 0)
                    return 0;
            }
            
        }
        
        return visited[m-1][n-1];
    }
    
}


class InputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public char readCharacter() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}
}

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(outputStream);
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void println(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}
