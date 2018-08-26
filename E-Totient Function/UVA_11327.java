/*
 * Given the series : 0/1, 1/1, 1/2, 1/3, 2/3, 1/4, 3/4, 1/5, 2/5, 3/5, 4/5... find the Kth fraction
 * 
 * Above problem is a nice application of Euler's Totient Function (φ)
 * We can observe that the number of times a fraction with the same denominator occurs = φ(denominator)
 * Edge cases like for φ(1) can be handled exclusively. Trivially, φ(1) = 1, but here φ(1) is taken to be 2. Since we have both 0/1 and 1/1
 * We try to find the denominator first and then the numerator of the Kth fraction
 * 
 * FINDING DENOMINATOR :
 * 		
 * 		Method 1:
 * 			Keep summing up φ(i) for all integers starting from 1 and stop when the sum is >= K
 * 			The denominator is that integer 'i' for which adding it's φ value resulted in sum >= K
 * 			
 * 		
 * 		Method 2:
 * 			Very similar to the previous method. Note that the denominator isn't as huge as the K value can get.
 * 			Given the constraints for 'K' we come up with an estimate of the max denominator, which can be probably around 200000 ( we go till 200031) here
 * 			Hence, we can form a cumulative sum array which stores the sum of φ values upto that index and then perform a binary search to 
 * 			get the smallest index at which the cumulative sum >= K. That index is the required denominator
 * 					
 * 		Note that in both the above methods, we need to pre-compute the φ values for integers upto 200000, in order to avoid computing it everytime.
 * 		Here, we first create a sieve of primes and along with storing each prime, we also store prime factors for the non-primes.
 * 		This simplifies the process of finding the φ values for the integers, since we stored the prime factors previously.
 * 		After that we can choose to opt for either do a linear scan (i.e method 1) or make another cumulative sum array and do a binary search (i.e method 2)
 * 	
 * FINDING NUMERATOR :
 * 		
 * 		Once we got the denominator 'd'. The (k - Sum(φ(i)))th number that's coprime to 'd', where 'i' goes from 1 to d-1, is the required numerator
 * 		since for each denominator, the mini-series of fractions with that denominator always starts with 1/d (except for d = 0)
 * 		we can simply output "1/d" if (k - Sum(φ(i))) results in '1'. Let's call (k - Sum(φ(i))) as N.
 * 		Thus goal is to find the Nth comprime to 'd'
 * 
 * 		Method 1 : 
 *			For all numbers 'i' upto 'd' compute the gcd(i,d). If gcd equals to '1', we know that number is coprime, so we increment our count
 *			Once count hits 'K', we stop and the number in consideration is the requried numerator
 *
 *		Method 2 :
 *			We are interested in finding the Nth coprime to 'd' which is equivalent to finding the smallest 'X' [ 1 < X < d ] such that 
 *			the count of the #comprimes to 'd',  till X is <= K 
 *			eg : suppose d = 6 , coprimes to 6 are [1,5] only
 *			Suppose K = 1, then X will be 1, and suppose K = 2, X will be 5
 *			Supoose d = 8, coprimes to 8 are [1,3,5,7], and k = 3 say
 *			then X will be 5 because 5 is the smallest number in (1,8) such that the number of coprimes till 5 (inclusive) is equal to 3, i.e {1,3,5}
 *			Now finding this 'X' can be done using Binary Search approach!
 *			Suppose we are able to 'count' the number of coprimes to 'd', till a particular 'limit'. We can apply Binary Search as follows :
 *				limit is first mid of (1,d) if count(limit) >= K, we reduce our search to (1,mid] else, search in [mid+1,d) and so on
 *			
 *			Now we come to the part of counting number of coprimes till a particular 'limit'
 *			We apply INCLUSION-EXCLUSION Principle to compute this :
 *				Suppose d = 30, it's prime factors are {2,3,5} Thus coprimes of 'd' means those numbers who don't share a prime factor in {2,3,5}
 *				Thuse we can write the number of coprimes till a limit X as = X - (X/2) - (X/3) - (X/5) + (X/6) + (X/15) + (X/10) - (X/30) 
 *				basically 'crossing' out the all multiples (numbers who have a factor) of 2,3 and 5 till X 
 *			
 * 				In order to do this, we generalize the expression as : say prime factors as P = {p1, p2...pn}
 * 					X/(subsets of size 1)			  X/(subsets of size 2)						X/(subset of size n)	
 * 				X - [(X/p1) - (X/p2) ...] + [(X/(p1*p2)) + (X/(p2*p3)) + (X/(p1*p3))...] +/- ... [X/(p1*p2*...pn)]
 * 					
 * 				Observe that the sign depends on the whether the subset size is even or odd! (if even, then +ve, if odd, then -ve)
 * 				Now if the number of elements in the set P is 'n', then the number of possible subsets can be 2^n 
 * 				Also, observe that the size of the set P in our problem can't go too big, generally of size 6, since the smallest number which has
 * 				7 distinct prime factors is (2*3*5*7*11*13*17) = 510510 which is well over the 200031 denominator limit we found out previously, thus
 * 				the max number of distinct prime factors will be 6, therefore max(n) = 6, hence total possible combinations will be 2^6 = 64
 * 				
 * 				Now our algo works as follows :
 * 					Choose a subset from P and do -->[ X/(product of elements in that subset) ]and the sign = number of elements in that subset
 * 					Total possible subsets will be at max 2^n.
 * 					We loop with a 'state' variable that goes from 1 to 2^n - 1. where if state = 1 :
 * 						
 * 					i.e state = 1 = 	...0 		0 		0 		0 		0 		1 
 * 										...   					   pn-2	   pn-1		pn
 * 
 * 					i.e each bit corresponds to the 'state' of whether the corresponding prime factor is put in the subset or not.
 * 					since the number of possible subsets are at max (2^n - 1) if we exclude the case where none are selected
 * 					we get a nice way to implement the algorithm!
 * 					
 * 					eg : all 1's (i.e the 'state' 2^n - 1) indicate that all the prime factors are included in the subset
 * 					state 2 indicates that 2nd last prime is only included... state 3 indicates pn-1 and pn are selected, rest are not.. and so on!
 * 
 * 					Getting the corresponding bit from a number is just a matter of right shifting the number specifiic number of times
 * 					We can even switch up the correspondance such that the LSB corresponds to p1 instead of pn and so on!
 * 
 * 				This way we can implement the inclusion-exclusion expression.
 * 
 *  After having found out both the numerator and the denominator, we can output them in a string format.
 */
import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws java.lang.Exception {
		final InputReader in = new InputReader(System.in);
		final OutputWriter out = new OutputWriter(System.out);

		long t = in.readLong();
		IrreducibleFractionSolution sol = new IrreducibleFractionSolution ();
		while (t != 0) {
			out.printLine(sol.getFraction(t));
			t = in.readLong();
		}
		out.flush();
		out.close();

	}
}

class IrreducibleFractionSolution {
	// phi array to store the phi values
	int[] phi;
	// Araylist to store the prime factors for each integer
	ArrayList<Integer>[] p;
	
	IrreducibleFractionSolution (){
		// precompute the phi values for the integers upto 200031 since as mentioned, the denominator doesn't get as huge as 'K' value
		calcPhi(200031);
	}

	String getFraction(long k) {
		// trivial case
		if (k == 1)
			return "0/1";
		long countPhis = 0;
		int numer = 0;
		int denom = 0;	
		
		// while loop to keep adding the phi values until < K
		while (true) {
			denom++;
			int val = phi[denom];
			if (countPhis + val >= k)
				break;
			else
				countPhis += val;

		}
		
		// the denominator is the integer at which the loop breaks
		// now we need to find the (k - countPhis)th coprime to denom
		int n = (int) (k - countPhis);
		if (n == 1) {
			// 1st coprime is 1
			return "1/" + denom;
		}
		
		// get the Nth (i.e k - countPhis)th coprime to denom
		numer = getNthCoprime(n, denom);
		
		// return numer and denom in string format
		return numer + "/" + denom;
	}

	int countCoprimesTill(int valTill, int coprimesTo) {
		// here valTill represents the smallest 'X' we talked about in the algorithm
		int res = valTill;
		int num_pfactors = p[coprimesTo].size();
		// we iterate over all the 'subset states' of the prime factors, to be used in the inclusion-exclusion expression
		// 'i' represents the 'subset state' that goes from 1 to 2^n - 1, where n is the number of prime factors of the denom (i.e coprimesTo)
		for (int i = 1; i < (1 << num_pfactors); i++) {	
			// product holds the product of the primes in the current subset
			int product = 1;
			// we also count the number of elements in the current subset
			// since we earlier stated that even # elements imply a +ve sign in (X/product_of_elements_in_current_subset), similarly for odd # elements
			int count = 0;
			// we iterate over all the prime factors and check if it can be included in the current subset or not
			// we do this by checking the 'correspondance' we described in the algo
			// subset state of '1' in binary is represented as : ...0001 i.e last prime factor should be included, rest not.
			// subset state of '2' in binary is represented as : ...0010 i.e 2nd last prime factor should only be included, rest not.
			// subset state of '3' in binary is represented as : ...0011 i.e the last two prime factors should only be included, rest not.
			// and so on...
			for (int j = 0; j < num_pfactors; j++) {
				// right shifting the bits of the 'subset state' accordingly and checking if the LSB is set or not (by AND with 1) does the job
				if ((1 & i>>num_pfactors-1-j) == 1) {
					product *= p[coprimesTo].get(j);
					// keep track of the number of primes 'included' in the product/subset
					++count;
				}
			}
			
			// if the number of primes included was odd, we subtract
			if ((count & 1) == 1)
				res -= valTill / product;
			else
			// else we add
				res += valTill / product;

		}
		
		return res;
	}

	int getNthCoprime(int n, int x) {
		
		// use binary search to find the smallest 'X' such that we get atleast 'k' coprimes till X
		int l, r;
		l = 1;
		r = x;
		while (l < r) {
			int mid = l + r;
			mid >>= 1; // mid = (l+r)/2 
			int Q = countCoprimesTill(mid, x);
			if (Q >= n)
				r = mid;
			else
				l = mid + 1;
		}
		return l;

	}

	void calcPhi(int n) {
		// initialize the array and calc the prime factors/sieve
		phi = new int[n];
		calcPrimeFactors(n);
		
		// trivial cases
		phi[0] = 0;
		phi[1] = 2;
		
		// since we have found out the prime factors earlier, we can use the formula for finding φ
		// φ(n) where n = p1^a1 * p2^a2 * p3^a3 ... * pn^an ; equals to n(1 - 1/p1)(1 - 1/p2)...(1 - 1/pn) so, we just needed the prime factors p1,p2...pn
		for (int i = 2; i < n; i++) {
			phi[i] = i;
			for (int j = 0; j < p[i].size(); j++) {
				// one by one get all the prime factors and plug them in the formula
				int val = p[i].get(j);
				phi[i] -= (phi[i]) / val;

			}

		}
	}

	void calcPrimeFactors(int n) {
		// sieve all the prime numbers and as well as the prime factors of integers till n
		// the arraylist holds the prime factors
		p = (ArrayList<Integer>[]) new ArrayList[n];  
		for (int i = 0; i < n; i++) {
			p[i] = new ArrayList<Integer>();
		}
		p[1].add(1); // trivial case
		for (int i = 2; i < n; i++) {
			// if the integer in consideration has no prime factor, it implies that it itself is prime
			// if this integer's Arraylist size is >= 1, then it means it's not prime, so we 'continue' to the next iteration without doing anything
			if (p[i].size() >= 1)
				continue;	
			// if the integer is prime, we add itself to it's list of prime factors
			p[i].add(i);
			// next we include this prime in the list of prime factors of it's multiples
			for (int j = i * 2; j < n; j += i)
				p[j].add(i);
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

