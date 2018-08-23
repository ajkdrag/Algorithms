/* 
 * Sieve approach to compute Euler's Totient function (φ) for 1 to n.
 * We know that in order to compute φ fast, we find out the prime factors of n.
 * Finding prime factors can be done via the sieve approach as well. Hence we combine these two.
 * We make a sieve array of size n+1, because we need the φ value for n as well
 * Initialize the sieve with default values of (i - 1) 
 * One thing to note that is that for all prime numbers p,  φ(p) = (p - 1) 
 * While all other non primes j ( greater than 2 ) will have φ(j) < (j - 1)
 * e.g : φ(7) = 7 - 1 = 6 ; φ(8) = 8(1 - 1/2) = 4  which is less that (8 - 1) i.e 7
 * Thus recall that in the sieve approach for finding the primes ≤ n , we assume all numbers to be prime
 * Then one by cross out the multiples of the "primes" leaving the actual primes intact.
 * Similar approach will be used here :
 * We assume all elements to be prime and since for prime p, φ(p) = p - 1,
 * we can signify this by initializing the sieve with (i - 1) for all 'i' | 2 ≤ i ≤ n
 * Note that φ(1) = 1 is a trivial case
 * Next instead of crossing out multiples of these "primes", we compute the "intermediate φ result" for them.
 * e.g : suppose we want to find φ(10)
 * sieve (initialized with (i - 1)) : [0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 * we ignore the first two and start from i = 2;
 * since it's value is (2 - 1) i.e 1 , we know that 2 is a prime!
 * multiples of 2 are (excluding itself) : {4, 6, 8, 10}
 * Now, we know that if 'i' = p1^a1 * p2^a2 ... * pk^ak ; i.e prime factorization of 'i';
 * then, φ(i) = i(1 - 1/p1)(1 - 1/p2)...(1 - 1/pk).
 * Therefore for multiples of 2 i.e {4, 6, 8, 10} we have found one prime factor already, namely 2 itself!
 * Hence for them, p1 = 2, hence we have φ(n) = [n(1 - 1/2)](1 - 1/p2)...(1 - 1/pk) {n is a multiple of 2}
 * since we have only found p1 so far, we only compute n(1 - 1/2) and store it in sieve[n]
 * Thus, we obtain : [0, 1, 1, 2, '2', 4, '3', 6, '4', 8, '5']
 * Similarly, we check if the next number is a prime i.e is 3 prime? since sieve[3] = 3 - 1 = 2, Yes!
 * Therefore for multiples of 3 i.e [6, 9] we have found a prime factor, namely 3 itself!
 * Hence we again do the same task : φ(n) = n(1 - 1/p1)(1 - 1/p2)...(1 - 1/pk)
 * Now it may be the case that this '3' may be the 1st prime factor i.e p1 or p2 or so on.
 * If it is the 1st prime factor, then sieve[i] wasn't modified in any of the previous iterations
 * i.e sieve[i] still has the default value of (i - 1). Thus we simply do : sieve[i] = i(1 - 1/p1)
 * else sieve[i] was modified in previous iterations owing to which it stores some intermediate result
 * Note that this intermediate result will always be < (i - 1);
 * thus, we can always check if sieve[i] was modified or not by checking if sieve[i] < (i - 1) or not
 * Once we know that sieve[i] was modified previously, owing to which sieve[i] now contains :
 * part of the result i.e [i(1 - 1/p1)(1 - 1/p2)...] (1 - 1/pj) ... (1 - 1/pk) i.e the part within [ ];
 * Thus, we can simply multiply whatever sieve[i] contains so far with (1 - pj)
 * where pj is the current prime number whose multiple is 'i'
 * We repeat these steps for all "primes" found till n/2 because a non-prime can't have a prime factor > n/2
 * We finally return the sieve[] array that contains the φ(i) for all 'i' such that 1 ≤ i ≤ n
 */
 
class Phi_Sieve {
	int[] sieve(int n) {
		// create the sieve array to hold the φ values
		int[] sieve = new int[n + 1];
		
		// initialize the array such that they are all "primes" since for prime p, φ(p) = p - 1
		for (int i = 0; i < n + 1; i++) {
			sieve[i] = i - 1;
		}
		
		// fill in the trivial cases
		sieve[0] = 0;
		sieve[1] = 1;
		sieve[2] = 1;
		sieve[3] = 2;

		// we loop only till n/2 since, a non-prime can't have a prime factor > n/2
		for (int i = 2; i <= n / 2; i++) {
			// sieve[i] == i - 1 signifies that 'i' is prime!
			if (sieve[i] == i - 1) {
				// we now consider multiples of 'i' except itself i.e 2*i, 3*i... so on
				int j = i << 1;		// i<<1 == 2*i
				while (j < n + 1) {
					int val = sieve[j];
					// if sieve[j] hasn't been modified i.e 'i' is the 1st prime factor of 'j'
					// we fill sieve[j] with j(1 - 1/i)
					if (val == j - 1)
						sieve[j] = j - (j / i);
					else
						// else sieve[j] has some intermediate result, we simply do :
						// sieve[j] = sieve[j](1 - 1/i)
						sieve[j] = val - (val / i);

					j += i;	// j is now the next multiple of 'i' 
				}
			}
		}
		return sieve;
	}
}
