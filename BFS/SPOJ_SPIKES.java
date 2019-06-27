/*
  Problem at : https://www.spoj.com/problems/SPIKES/
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        InputReader ip = new InputReader(System.in);
        OutputWriter op = new OutputWriter(System.out);
        int r = ip.readInt();
        int c = ip.readInt();
        int l = ip.readInt();
        Solution sol = new Solution(r, c, l);
        for(int i = 0; i < r; ++i){
            for(int j = 0; j < c; ++j)
                sol.addCell(i, j, ip.readCharacter());
        }
        op.println(sol.solve());
        op.close();
    }
}

class Solution {
    char[][] graph;
    int[] visited;
    int[] dirX = {-1, 0, 1, 0};
    int[] dirY = {0, -1, 0, 1};
    HashSet<Integer> srcs;
    Queue<Node> queue;
    int nRows;
    int nCols;
    int spikeLimit;
    
    Solution(int rows, int cols, int limit){
        nRows = rows;
        nCols = cols;
        spikeLimit = limit;
        graph = new char[nRows][nCols];
        visited = new int[nRows*nCols];
        srcs = new HashSet<Integer>();
    }
    
    void addCell(int x, int y, char c){
        graph[x][y] = c;
        if(c == '@')
            srcs.add(x*nCols + y);
    }
    
    String solve(){
        for(int src : srcs){
            if(bfs(src))
                return "SUCCESS";
        }
        return "IMPOSSIBLE";
    }
    
    boolean bfs(int src){
        queue = new LinkedList<Node>();
        queue.add(new Node(src, 0));
        visited[src] = 1;
        
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            int currNodeX = curr.nodeVal/nCols;
            int currNodeY = curr.nodeVal%nCols;
            int currSpikes = curr.spikesSoFar;
            visited[curr.nodeVal] = 1;
            
            for(int i = 0; i < 4; ++i){
                int newX = currNodeX + dirX[i];
                int newY = currNodeY + dirY[i];
                if(newX < 0 || newY < 0 || newX >= nRows || newY >= nCols || visited[newX*nCols + newY] == 1)
                    continue;
                int graphVal = graph[newX][newY];
                if(graphVal == 'x')
                    return true;
                if(graphVal == 's')
                    ++currSpikes;
                if(graphVal == '#' || currSpikes > (spikeLimit>>1))
                    continue;
                queue.offer(new Node(newX*nCols + newY, currSpikes));
            }
        }
        
        return false;
    }
}

class Node {
    int nodeVal;
    int spikesSoFar;
    
    Node(int n, int s){
        nodeVal = n;
        spikesSoFar = s;
    }
}

// fast I/O
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


