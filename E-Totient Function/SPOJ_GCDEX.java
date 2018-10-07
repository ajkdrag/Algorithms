/*
  Problem at : https://www.spoj.com/problems/GCDEX/
  
  The Problem statement basically wants us to find the sum of gcd(i,n) where 1 <= i < n 
  This can be found out using Euler's Totient function and the formula for this gcd sum :
  
                  d * φ(n/d)      where 'd' are all divisors < n
  
  Here since 'i' never equals 'n' so we won't take 'd == n' as one of the divisors of 'n'
  For this first we find the φ values for all numbers upto the defined limit.
  Next since we need gcdSum till 'x' where 1 < x <= 1000001
  We sieve all the results into an array. In other words, we need G(x) = GCDSUM(2) + GCDSUM(3)... GCDSUM(x) where 1 < x <= 1000001
  Note that G(x) = GCDSUM(x) + G(x - 1) Thus we can do cumulative sum afterwards to precompute for all 'x' in [2, 1000001]
  Now to find GCDSUMs we use a divisor sieve.
    Here, we start 'i' with 1 and 'j' with 2 and thus get i*j = 2 implying that for n = i*j, d = i, and n/d = j
    ==> GCDSUM = sum(i * φ(n/i)) = sum(i*φ(j)) where 'i' and 'j' are such that n = i*j (same meaning as 'i' is a divisor of 'n')
    So suppose when i = 2, j = 6, we would add i*φ(j) to GCDSUM_array[i*j = 12]
    Similarly when i = 3, j = 4, we would add i*φ(j) to GCDSUM_array[i*j = 12]
    Thus in this way, we would be sieving all the partial sums 'i' always starts with '1' thus including 1 as a divisor
    and 'j' starts with '2' and not with '1', thereby excluding the divisor 'd == n'  (because if i == n, then j will be 1)
    
 Thus, Once we are done filling the array with GCDSUMs we can then do a cumulative sum on it to find 'G(i)' and return the
 result of our queries in O(1), since all the precomputing has been done and we are not calculating the results on every query.
*/


import java.io.*;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws IOException{
        final InputReader in = new InputReader(System.in);
		final OutputWriter out = new OutputWriter(System.out);
        SolutionGCDEX.precompute(1000001);
		int t = in.readInt();
		while (t != 0) {
			out.printLine(SolutionGCDEX.precompG[t]);
			t = in.readInt();
		}
		out.flush();
		out.close();
    }
}

class SolutionGCDEX {
    // Arrays for storing φ and GCDSUMs
    static int[] phiSieve;
    static long[] precompG;
    
    static void precompute(int N){
        // find φ values for all the odd numbers till N
        fillPhiSieve(N+1);
        precompG = new long[N + 1];
        // sieve the GCDSUMs as stated in the algorithm
        for (int i = 1; i <= N; ++i) { 
            for (int j = 2; i * j <= N; ++j) 
                {
                    long temp = i * getPhi(j);
                    precompG[i * j] += temp; 
                }
        } 
        // perform a cumulative sum on the sieved values to get G(i)
        for (int i = 2; i <= N; i++) 
            precompG[i] += precompG[i - 1]; 
    }
    
    // for even numbers we can get their φ values using:
    // φ(2*k) = 2*φ(k) if 'k' is even
    // φ(2*k) = φ(k) if 'k' is odd
    // This is simply done to save some space and not having to sieve all numbers upto n
    // We could have easily sieved for all numbers (even and odd) but this works fine as well.
     static int getPhi(int n){
        if((n&1) == 1) return phiSieve[n>>1];
        int res = 1;
        int count = -1;
        while((n&1) == 0) {
            n >>= 1;
            ++count;
        }
        return phiSieve[n>>1]<<count;
    }
    
    // Sieve for finding the φ values for only odd numbers
    static void fillPhiSieve(int limit){
        int len = (limit + 1) >> 1;
        phiSieve = new int[len];
        
        phiSieve[0] = 1;
        phiSieve[1] = 2;
        
        for(int i = 2; i < len; ++i){
            phiSieve[i] = i<<1;
        }
        
        for(int i = 3; i <= limit>>1; i += 2){
            if(phiSieve[i>>1] == i - 1){
                for(int j = (i<<2) - i; j <= limit; j += i<<1){
                    int val = phiSieve[j>>1];
                    if(val == (j - 1)) {
                        phiSieve[j>>1] = j - j/i;
                    }
                    else phiSieve[j>>1] -= val/i;
                }
            }
        }
    }
    
}

// custom input output class for fastIO
class InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

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

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public double readDouble() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		double res = 0;
		while (!isSpaceChar(c) && c != '.') {
			if (c == 'e' || c == 'E')
				return res * Math.pow(10, readInt());
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		}
		if (c == '.') {
			c = read();
			double m = 1;
			while (!isSpaceChar(c)) {
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, readInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				m /= 10;
				res += (c - '0') * m;
				c = read();
			}
		}
		return res * sgn;
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

	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}

class OutputWriter {
	private PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void printLine(Object... objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}

	public void flush() {
		writer.flush();
	}
}

