/*
  Problem at : https://www.spoj.com/problems/COMDIV/
*/

import java.io.*;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws IOException{
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        Solution sol = new Solution();
        sol.fill();
        int t = in.readInt();
        while(t-- > 0){
            out.printLine(sol.solve(in.readInt(), in.readInt()));
        }
        out.close();
    }
}

class Solution {
    int limit = 1000000;
    int[] divisors;
    
    void fill(){
        divisors = new int[limit + 1];
        divisors[1] = 1;
        for(int i = 2; (i<<1) <= limit; ++i){
            if(divisors[i] == 0){
                for(int j = i<<1; j <= limit; j+=i){
                    int ct = 0;
                    int cpy = j;
                    while((cpy%i) == 0){
                        cpy/=i;
                        ++ct;
                    }
                    int curr = divisors[j];
                    if(curr == 0){
                        curr = 1;                        
                    }
                    curr *= (ct + 1);
                    divisors[j] = curr;
                }
            }
        }
    }
    
    int gcd(int a, int b){
        if(b==0)
            return a;
        return gcd(b, a%b);
    }
    
    int solve(int a, int b){
        int d = divisors[gcd(a, b)];
        return d == 0? 2 : d;
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

	public void printLine(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}


