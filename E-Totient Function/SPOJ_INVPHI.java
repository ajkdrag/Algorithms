import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) throws java.lang.Exception {
		final InputReader in = new InputReader(System.in);
		final OutputWriter out = new OutputWriter(System.out);
		int t = in.readInt();
		SolutionInvPhi sol = new SolutionInvPhi();
		while (t-- > 0) {
			int n = in.readInt();
			int res = sol.getInvPhi(n)[0];
			out.printLine(res);
		}
		
		out.flush();
		out.close();

	}
}

class SolutionInvPhi {
	int[] availList;
	HashMap<Integer, int[]> invPhiMap;
	int[] sieve;

	static final int LIMIT = 2147483647;
	static final int PRIME_LIMIT = 44725;
	static final int CACHE_LIMIT = 100000;
	static final int[] FERMAT_TEST_PRIMES = { 2, 3, 5, 7, 11, 13 };

	SolutionInvPhi() {
		fillSieve((PRIME_LIMIT >> 6) + 1);
		fillAvailList((CACHE_LIMIT >> 6) + 1);
	}

	int[] getInvPhi(int k) {
		int[] res = new int[2];
		res[0] = -1;
		res[1] = -1;

		if (k == 1) {
			res[0] = 1;
			res[1] = 2;
			return res;
		}
		if (k == 2) {
			res[0] = 3;
			res[1] = 4;
			return res;
		}
		if ((k & 1) == 1)
			return res;

		int first = LIMIT;
		int second = -1;

		if (isPrime(k + 1)) {
			first = k + 1;
		}

		if (k <= CACHE_LIMIT) {
			if ((availList[k >> 6] & (1 << ((k & 63) >> 1))) == 0) {
				return res;
			}

			else if (invPhiMap.containsKey(k)) {
				return invPhiMap.get(k);
			}

		}

		for (int i = 2; mulModOverflow(i, i, LIMIT) <= k; i += 2) {
			if (k % i == 0) {
				int result = 0;
				int A = i;
				int B = k / i;
				if ((B & 1) == 1) {
					// todo
					if (isPrime(A + 1)) {
						int exp = getExp(A + 1, B);
						if (exp == -1)
							continue;
						else {
							result = mulModOverflow(A + 1, B, LIMIT);
							second = result;
						}
					} else
						continue;
				} else {
					if (A == B) {
						A = getInvPhi(A)[0];
						B = getInvPhi(B)[1];
					} else {
						A = getInvPhi(A)[0];
						B = getInvPhi(B)[0];
					}

					if (A == -1 || B == -1 || gcd(A, B) > 1)
						continue;
					result = mulModOverflow(A, B, LIMIT);

				}

				if (result < first) {
					first = result;
				}
			}
		}

		if (first == LIMIT) {
			if (k <= CACHE_LIMIT){
				availList[k >> 6] &= ~(1 << ((k & 63) >> 1));
			}
			if(k != LIMIT - 1) return res;
		}

		res[0] = first;
		res[1] = second;
		invPhiMap.put(k, res);

		return res;
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int t = a;
			a = b;
			b = t % b;
		}
		return a;
	}

	boolean isPrime(int n) {
		if (n == 2147483647)
			return true;
		if ((n == 1) || ((n > 2) && (n & 1) == 0))
			return false;
		if (n <= PRIME_LIMIT)
			return ((sieve[n >> 6] & (1 << ((n & 63) >> 1))) != 0);
		for (int i = 0; i < 6; ++i) {
			if (FERMAT_TEST_PRIMES[i] >= n)
				continue;
			if (fastExpMod(FERMAT_TEST_PRIMES[i], n - 1, n) == 1)
				continue;
			return false;
		}
		return true;
	}

	int mulMod(int num, int times, int limit) {
		// TODO!
		long temp = num;
		long res = 0;
		while (times > 0) {
			if ((times & 1) == 1)
				res = (res + temp) % limit;

			temp = (temp << 1) % limit;

			times >>= 1;
		}

		return (int) res;
	}

	int mulModOverflow(int num, int times, int limit) {
		// TODO!
		long temp = num;
		long res = 0;
		while (times > 0) {
			// If b is odd, add a with result
			if ((times & 1) == 1) {
				res = (res + temp);
				if (res >= limit)
					return limit;

			}
			temp = (temp << 1) % limit;

			times >>= 1;
		}

		return (int) res;
	}

	int fastExpMod(int base, int exp, int limit) {
		int res = 1;
		if (exp == 1)
			return base;
		while (exp > 0) {
			if ((exp & 1) == 1)
				res = mulMod(res, base, limit);
			base = mulMod(base, base, limit);
			exp >>= 1;
		}
		return res;
	}

	int getExp(int base, int num) {
		if (base == num)
			return 1;
		int temp = base;
		int res = 1;
		while (temp < num) {
			temp = mulModOverflow(temp, base, LIMIT);
			++res;
		}
		return (temp == num) ? res : -1;
	}

	void fillSieve(int len) {
		this.sieve = new int[len];
		for (int i = 0; i < len; i++) {
			sieve[i] = 0xffffffff;
		}

		sieve[0] &= ~1;

		int i = 3;
		for (; i * i <= PRIME_LIMIT; i += 2) {
			if ((sieve[i >> 6] & (1 << ((i & 63) >> 1))) != 0) {
				for (int j = i * i; j <= PRIME_LIMIT; j += i << 1) {
					sieve[j >> 6] &= ~(1 << ((j & 63) >> 1));
				}
			}
		}

	}

	void fillAvailList(int n) {
		this.availList = new int[n];
		for (int i = 0; i < n; i++) {
			availList[i] = 0xffffffff;
		}
		invPhiMap = new HashMap<Integer, int[]>();
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
