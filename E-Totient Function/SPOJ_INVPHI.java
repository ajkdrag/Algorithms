/*
	Problem at : https://www.spoj.com/problems/INVPHI/
	Basically given 'k', we have to find the smallest 'n' such that φ(n) = k
	
	Trivial cases : All 'k' values must be even except for 1, because φ(x) = even except for x = 1 or x = 2;
	The algo starts by first creating a sieve of primes upto a certain limit ( around sqrt(Integer.MAX_VALUE) )
	The sieve is a bitmask sieve thereby occupying less space.
	Next we also have some helper multiplication and exponentiation methods that deal with overflows as well
	We also have a fermat prime checker for values that are beyond our sieve limit.
	
	Now, given k, there can be 3 cases :
	
	CASE 1 : 'n' is a prime, such that k is one less than n, i.e if we find that 'k + 1' is a prime, that is the smallest 'n'.
	
	CASE 2 : 'n' itself is not a prime .i.e we can prime factorize it into something as : p1^a1 * p2^a2 * p3^a3 ...
		  i.e two or more than two prime factors of 'n' exist.
		  In this case, we have p1, p2 .. are primes thus we can break 'n' into two pieces as :
		 
		  φ(n) = φ(p1^a1) * φ(p2^a2 * p3^a3 ...)
		  or
		  φ(n) = φ(p1^a1 * p2^a2) * φ(p3^a3 ...) so on.
		  
		  i.e , we can have many partitions of size two like above, note that since φ values are even (except 2 and 1)
		  we need to split 'k' into two even values 'a' and 'b' such that a*b = k
		  Then the smallest IET value of 'k' will be the product of smallest IET values of 'a' and 'b' (recursive calls)
		  provided that the inverse values are coprime i.e their gcd is 1. In order to find this 'a' and 'b', we check 
		  all even divisors of 'k'. Say 'i' is one such even divisor, then 'k/i' will be our 'b' satisying 'a*b = k'
		  Now we find the smallest IET values of 'i' and 'k/i' recursively, and make sure that their gcd is 1
		  Similarly we compute for other even divisors. The minimum among them shall be the required 'n'.
		  
	CASE 3 : 'n' itself is not a prime but it has only one prime factor i.e ( n = p1^a1 )
		  In such a case, we φ(n) = φ(p1^a1) = p1^(a1 - 1) * (p1 - 1)
		  Apart from 2, all other primes are odd, hence (p1^a1) is odd ==> φ(n) will be of the form (even * odd) 
		  Thus, we also need to make sure, that if (k/i) is odd, then whether 'n' is from CASE 3 or not.
		  We can do this observing that even part i.e 'i' should correspond to (p1 - 1) and odd part i.e (k/i)
		  should correspond to (p1^(a1 - 1)) 
		  In other words i + 1 should be a prime such that (k/i) is some power of that prime
		  
		  Now if we consider the case for p1 being 2 i.e an even prime, in that case the split p1^(a1 - 1)*(p1 - 1)
		  evaluates to 2^(a1 - 1)*(2 - 1) ==> 2^(a1 - 1) i.e 'k' is a power of 2, and 'n' will be 2 * k
		  i.e we just need to check if 'k' is a power of 2 or not and then we will have possible value for n as 2 * k .
		  
	
	Now comes a very crucial edge case; we know that 'k' has to be even (ignore trivial cases of 1, 2) or in general, composite.
	If we considered the greedy approach of solving for 'n', we broke 'k' down to two factors, and found minimum n for both
	these factors and multiplied them (if their gcd == 1), Now suppose 'n' is such that it belongs to CASE 2, Let's analyze it:
	
	Say, 'n' has two prime factors i.e n = p1^a1 * p2^a2  { p1 < p2 and a1, a2 > 0 ==> p2 can't be 2}
	and φ(n) = φ(p1^a1) * φ(p2^a2) and it happens that these "sub-phi" values evaluate to equal numbers.
	Note that if they are not equal, then both are even and we follow as mentioned in CASE 2 to compute 'n'
	Now, if these numbers happen to be equal then we have :
	
	p1^(a1 - 1) * (p1 - 1) == p2^(a2 - 1) * (p2 - 1) 		-----------> (1)
	
	Since p1 < p2 and p1, p2 are primes LHS can never have the prime factor p2 because (p1 - 1) always has prime factors
	less than p1 and p1 itself is less than p2. Also, p1^(a1 - 1) can never contain p2 because p1 itself is a prime.
	Thus LHS can never have p2. So in order to satisfy the equality, RHS term also mustn't contain p2.
	i.e. a2 = 1, 
	==> p2^(a2 - 1) * (p2 - 1) evaluates to simply (p2 - 1) 
	
	Thus, (1) becomes : 
	
		p1^(a1 - 1) * (p1 - 1) == (p2 - 1)
	
	==>	φ(p1^a1) + 1 = p2					-----------> (2)
	
	This implies that if we have a prime p1 raised to some power a1 and another prime p2 such that it satisfies (2),
	then φ(p1^a1 * p2) is always a perfect square. This allows us perform an extra check if 'k' happens to be a perfect square
	We check that if sqrt(k) is even and if sqrt(k) + 1 is prime and also if IET value of (sqrt(k)) can be of the form p1^a1
	or we can also say that sqrt(k) can also be of the form -->	p1^(a1 - 1)(p1 - 1);
	Then we need to put the number (sqrt(k) + 1) * (p1 ^ a1)) into our list of possible values of 'n'.
	
	Now, so far we have been dividing 'k' into two factors and greedily computing 'n'.
	What if out solution could only be found by partitioning 'k' into more than two factors? say 3 factors.
	Then suppose the 3 factors are : a, b and c :
	
	i.e	k = a * b * c
	
	Then our solution 'n' must also have been partitioned into 3 co-prime numbers say p, q and r :
	
	i.e 	n = p * q * r  ==> φ(n) = φ(p) * φ(q) * φ(r) = a * b * c
	
	but then, since p, q and r are co-prime, we could have chosen to "not partition n into three, but instead only two" :
	
	i.e 	φ(n) = φ(p * q) * φ(r) 	  or 	φ(n) = φ(p) * φ(q * r)
	
	Thus, k = (a * b) * (c)    or     k = (a) * (b * c)  	which boils down to two factors.
	Thus, we could have reached 'n' by analyzing two factors only.
	
	Now, suppose the solution 'n' can be reached only when 'k' is split into two factors such that they are equal
	i.e sqrt(k) = x (say)
	
	k = x * x 
	
	we earlier considered the case when 'n' had two prime factors only, let's now consider 'n' to have 3 factors p, q, r :
	
	n = p^a * q^b * r^c 
		
	Let,   φ(n) = φ(p^a) * φ(q^b * r^c) be the split that gives the two equal values 'x',

	φ(p^a) == x    			==> 	p^(a - 1) * (p - 1) == x				-----------> (3)
	φ(q^b * r^c) == x 		==>     q^(b - 1) * (q - 1) * r^(c - 1) * (r - 1) == x		-----------> (4)
	
	Now, We could have chosen to split it as : φ(n) = φ(p^a * q^b) * φ(r^c), which will produce : A and B say, i.e
	
	φ(p^a * q^b) == A	==> 	p^(a - 1) * (p - 1) * q^(b - 1) * (q - 1) == A
	φ(r^c) == B		==>	r^(c - 1) * (r - 1) == B
	
	Clearly, A is larger than x and B is smaller than x
	Thus we could have obtained the solution 'n' with unequal partitioning as well, thereby contradicting our assumption.
	Thus, when 'n' has more than 2 prime factors, we can reach 'n' with unequal partitioning of 'k'. 
	In other words, we only need to factor 'k' into two numbers, and if 'n' can only be reached when the two numbers are equal,
	then 'n' has only two factors! one of them is (sqrt(k) + 1) and the other obtained from relation (2).
	
	Thus, we can take these cases into account and write a recursive solution to compute the smallest IET value for 'k'
	
							# # # # # # # # # #
	----------------------------------------------- #  OBSERVATIONS:  # ------------------------------------------------
							# # # # # # # # # #
							
							
	From (2), we have a conjecture that : There are infinite primes of the form : φ(p^a) + 1
	A consequence of this conjecture is that there are infinite numbers such that their totient values are perfect squares.
	
	Suppose we have 'n' as : p^a * q^b * r^c { p < q < r and a, b, c > 0 }
	and φ(p^a) == φ(q^b * r^c)
	
	==> p^(a - 1)*(p - 1) == q^(b - 1)*(q - 1) * r^(c - 1)*(r - 1)
	
	Since, p < q < r, q and r can never occur in LHS. Thus, b = 1 and c = 1
	
	==> p^(a - 1)*(p - 1) == (q - 1) * (r - 1)
	
	==> p * p * p * p ... (a - 1) times = (q - 1) * (r - 1) / (p - 1)			-----------> (5)
	
	Consider the case when either (q - 1) is a multiple of (p - 1) or (r - 1) is a multiple of (p - 1);
	Note that in this case, let's say : (q - 1) = k * (p - 1) 
	Note that k must be p or some power of p because if it has some other prime factor, then we won't be able to cancel it out
	and LHS can't be equal to RHS in that case.
	
	Thus, we have : 
	
	==> p * p * p * p ... (a - 1) times = k * (r - 1)
	
	Since p < q < r, r has to be an odd prime,
	this means that r - 1 is an even number lets say: (r - 1) = 2*s
	
	==> p * p * p * p ... (a - 1) times = 2 * k * s
	
	Since LHS is composed of one single prime i.e p,
	p must be equal to 2 
	
	More over, k and s must be powers of 2 as well following the same logic,
	i.e 2^(a - 1) = 2 * k * s								------------> (6)
	==> k * s = 2^(a - 2)		
	Thus k and s must be powers of 2 such that their exponents add up to (a - 2)	
	
	Now, p = 2, 
	q - 1 = k *(p - 1)
	==> q - 1 = k
	==> q = k + 1
	i.e q = (some power of 2) + 1 								------------> (7)
	in other words, q is a Fermat prime.
	
	similarly, 
	r - 1 = 2 * s
	==> r = 2 * s + 1
	i.e r = (2 * some power of 2) + 1							------------> (8)
	i.e r = (some other power of 2) + 1
	in other words, r is also a Fermat prime.
	
	Let's take an example for illustration :
	Let's take two fermat primes, --> 17 (i.e 2^4 + 1) and 257 (i.e 2^8 + 1)
	thus, we have our p = 2 and q, r as 17 and 257 respectively,
	we get k = 2^4, and s = 2^7 from (7) and (8) respectively.
	We also have from (6) : a - 2 = (4 + 7) i.e a = 13
	
	Thus our number 'n' which can be partitioned in two "sub-phis" that give equal values is (p^a * q * r)
	i.e  n = 2^13 * 17 * 257 = 35790848
	and it's totient value will be : square of ((q - 1) * (r - 1))  = square(16 * 256) = 16777216
	
	-------------------------------------------------------------------------------------------------------------------
	
	Another observation is that there are infinite numbers (even) such that they have no totient inverses.
	i.e there are even numbers 'x' such that there is no 'n' for which φ(n) = x
	eg : 14, 26, etc.
	Let's consider an even number 'x' of the form 2*(p^a) (p > 2 and p is an odd prime)
	Thus, if this number is totient value of some number 'n', we have : φ(n) = 2*(p^a)
	Now 'n' can either be a prime such that φ(n) = n - 1 = 2*(p^a)
	
	==> n = 2*(p^a) + 1  and n is a prime   -------------> (9)
	
	Let's say 'n' is not a prime, in that case, it can be written as : n can be factored into product of prime powers
	Now since, x = 2*(p^a) ==> the prime power in 'x' i.e p^a must have come from 'n' 
	Also note that since 'x' is a product of 2 and an odd number, it has only one '2' in it.
	This implies that 'n' cannot have more than one odd prime factor. Infact 'n' has only one odd prime factor.
	Even if 'n' has 2 as one of it's prime factor, it can't be the case that 'n' is a factor of a power of 2 greater than 1.
	For now, let us only consider odd primes in our discussion.
	Thus, we have : n = p^(a + 1) 
	==> φ(n) = p^a * (p - 1) = 2 * (p^a)
	==> p - 1 = 2
	==> p = 3
	Thus p = 3 is the only prime number that satisfies such a criteria.
	In other words there are no other primes 'p' such that 2*(p^a) has an inverve image in φ (if (9) doesn't hold true)
	If we take an odd prime that's not 3 such that 2*(p^a) + 1 isn't a prime, then 2*(p^a) doesn't have an inverse in φ
	('a' is any whole number). if a = 0, 2*(p^a) + 1 = 3 which is a prime, so better to take a >= 1
	
	So, now let's choose some odd primes (not equal to 3), say p = 5,
	if (a = 1), we have 2*(5^a) + 1 = 11 which is a prime,
	if (a = 2), we have 2*(5^2) + 1 = 51 which is not a prime.
	==> 2*(5^2) = 50 doesn't have an inverse image in φ 
	if (a = 3), we have 2*(5^3) + 1 = 251 which is a prime.
	if (a = 4), we have 2*(5^4) + 1 = 1251 which is not a prime.
	==> 2*(5^4) = 1250 doesn't have an inverse image in φ
	
	Observe that, 14, comes when we pick p = 7 and a = 1, 2*(7^a) + 1 = 15 which is not prime ==> 2*7 = 14 has no inverse in φ
	Similarly, we can check for other primes and their powers as well.
	
	Now, let's consider 'x' to be of the form 2*(p^a)*(q^b) (p < q and p and q are odd primes with a,b as +ve exponents)
	Again we check if 2*(p^a)*(q^b) + 1 is a prime or not. If it is a prime, we are done, 'x' has an inverse. If not we proceed.
	Since, again we have only one 2 in 'x', it has to be the case that 'n' has only one odd prime factor.
	Note that since 'x' has 'p^a' and 'q^b' in it. They must have come from 'n'. i.e 'n' is either 'p^(a + 1)' or 'q^(b + 1)'
	If n = p^(a + 1), φ(n) = p^a * (p - 1)
	==> p^a *(p - 1) = 2*(p^a)*(q^b)
	==> (p - 1) = 2*(q^b) which isn't possible since (p < q and a,b are positive numbers)
	
	Therefore, n = q^(b + 1), φ(n) = q^b * (q - 1)
	==> q^b * (q - 1) = 2*(p^a)*(q^b)
	==> q - 1 = 2*(p^a)
	==> q = 2*(p^a) + 1
	
	From this expression we can get the following results :
	1) If we already have a number of the 2*(p^a) which has no inverse in φ, we can get other numbers that don't have 
	   an inverse in φ, by multiplying with (q^b) where q is different odd prime > a, and b is a positive number.
	   Keep in mind that the number in consideration when added with 1, doesn't result in a prime.
	
	2) If the number 2*(p^a) is such that 2*(p^a) + 1 is a prime, even then we can generate a number that doesn't have an
	   inverse in φ as follows : Take any other prime 'P' > p, except 2*(p^a) + 1 and, if 2*(p^a)*(P^b) + 1 isn't a prime,
	   then 2*(p^a)*(P^b) has no inverse in φ, where b is a positive number.
	   
   	e.g : Previously we generated some numbers : 14, 26, 50, 1250 etc which have no inverse in φ
	14 = 2*7 
	Let's take a prime number greater than 7, say : 11 and a = 1
	==> 14*11 = 154 and 154 + 1 = 155 isn't a prime
	==> 154 is a number that has no inverse in φ
	If a = 2,
	==> 14*11*11 = 1694 and 1694 + 1 = 1695 in't a prime
	==> 1694 is a number that has no inverse in φ
	Similarly, we can obtain for other values of 'a', 'p', 'q' and 'b'
	
	Let's consider a number of the form 2*(p^a) which has an inverse in φ
	Say, that number is 2*(3^2) = 18 which has inverse images in φ
	It is seen that 18 + 1 = 19 which is a prime number. Thus excluding this prime, if we take any other prime P > 3
	such that 18 * (P^b) + 1 isn't a prime, we can say that 18*(P^b) has no inverse.
	Let's take P as 5 and b = 1,
	==> 18*(5^b) + 1 = 91 which is not a prime.
	==> 18*5 = 90 has no inverse in φ
	for b = 2,
	==> 18*(5^2) + 1 = 451 which is not a prime.
	==> 18*(5^2) = 450 has no inverse in φ
	Similarly, we can obtain for other values of 'a', 'p', 'P', 'b'
	
	We can extend this further in a similar fashion to 'x' of forms as : 2*(p^a)*(q^b)(r^c)...
	
	-------------------------------------------------------------------------------------------------------------------
	
	Recurrence relations for finding number of steps to reach 1
	i.e say n = 12,
	φ(12) = 4
	φ(4) = 2
	φ(2) = 1
	Thus, it took 3 steps for 'n' to reach 1.
	For any number 'n' , we have
	
	Case 1 : 'n' is odd : Steps(n) = Steps(φ(n)) + 1
	Case 2 : 'n' is even and 'n/2' is odd : Steps(n) = Steps(n/2)	(Since in this case φ(n) = φ(n/2))
	Case 3 : 'n' is even and 'n/2' is even : Steps(n) = Steps(n/2) + 1
	
	Proof of Case 3 :
	  	This can be proved analytically as follows :
		Let the steps 'k' follows to reach 1 be as follows (let the number of steps be 's')
		k --> φ(k) --> φ(φ(k)) --> φ(φ(φ(k))) ... 	--> 1

		Now, we consider 2*k and see it's steps as follows :
		2*k --> φ(2*k) --> φ(φ(2*k)) --> φ(φ(φ(2*k))) ... 
		which is equal to :
		2*k --> 2*φ(k) --> 2*φ(φ(k)) --> 2*φ(φ(φ(k))) ...

		Thus it is clear that after 's' steps, 2*k will end up at 2*1 i.e 2 and thus it needs one extra step to reach 1

		Hence, we have : Step(2*k) = 1 + step(k)
		
	-------------------------------------------------------------------------------------------------------------------
	
*/

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

		if((k & (k - 1)) == 0) second = mulModOverflow(k, 2, LIMIT);
		
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

					if (A == -1 || B == -1 || gcd(A, B) != 1)
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
