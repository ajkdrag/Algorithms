/*
  Problem at : https://www.spoj.com/problems/FACT0/
*/

// accepted

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Solution sol = new Solution();
        long n;
        while(true){
            if((n = in.readLong()) == 0)
                break;
            sol.compute(n);
            out.printLine(sol.sb.toString());
        }
        out.close();
        
    }
}

class Solution {
    ArrayList<Long> factors = new ArrayList<Long>(12);
    HashMap<Long, Long> factorsCnt = new HashMap<Long, Long>();
    StringBuilder sb = new StringBuilder();
    Random rand = new Random();
    
    long mult(long a, long b, long mod) {
        long res = 0;
        while(b > 0){
            if((b&1) ==1)
                res = (res + a)%mod;
            a = (a<<1)%mod;
            b >>= 1;
        }
        return res;
    }

    long f(long x, long c, long mod) {
        return (mult(x, x, mod) + c) % mod;
    }

    long gcd(long a, long b){
        if(b == 0)
            return a;
        return gcd(b, a%b);
    }
    
    long rho(long num, long x0, long c){
        if(num <= 1000)
            return trial(num);
        
        long t = x0;
        long h = x0;
        long d = 1;
        long diff = 0;
        int steps = 1000;
        while(d == 1 && steps-- > 0){
            t = f(t, c, num);
            h = f(f(h, c, num), c, num);
            diff = h > t ? h - t : t - h;
            d = gcd(diff, num);
        }
        return d;
    }
    
    long trial(long num){
        if((num&1) == 0)
            return 2;
            
        for(long i = 3; i*i <= num; i+=2){
            if((num%i) == 0)
                return i;
        }
        
        return num;
    }
    
    void compute(long num){
        long factor = 1;
        factors.clear();
        factorsCnt.clear();
        sb.setLength(0);
        long cpy = num;
        
        while(cpy != 1){
            factor = rho(cpy,2, 1);
            while(factor == 1)
                factor = rho(cpy, rand.nextInt(10), rand.nextInt(10));
            factor = trial(factor);
            
            long ct = 0;
            while((cpy%factor) == 0){
                cpy /= factor;
                ++ct;            
            }
            
            factorsCnt.put(factor, ct);
            factors.add(factor);
        }
        
        Collections.sort(factors);
        for(long f : factors){
            sb.append(f);
            sb.append('^');
            sb.append(factorsCnt.get(f));
            sb.append(' ');
        }
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

	public long readLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
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


