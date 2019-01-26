/*
  Problem at : https://projecteuler.net/problem=357
*/

public class Problem357 {
    static int[] prime;
    static int[] map;
    public static void main(String args[]) {
        long N = 100000000L;
        prime = new int[((int)N>>5) + 1]; 
        int[] map = new int[(int)N + 1];
        int lim = (int) Math.sqrt(N);
        long res = (N>>1)*(N+1);
        for (int i = 3; i  <= lim; i += 2) { 
            if ((prime[i>>5] & (1 << ((i >> 1) & 31))) == 0) {
            	map[i] = 1;
            	res -= i;
                for (int j = i * i, k = i << 1; j < N; j += k) 
                    prime[j>>5] |= (1 << ((j >> 1) & 31));
            }
        } 
        for(int i = 1; i <= lim; ++i){
            for(long j = i, last = N/i; j <= last; ++j){
                int prod = i*(int)j;
                int sum = i + (int)j;
                if(map[prod] == 1)
                	continue;
                if(((sum&1)==0) || ((prime[sum>>5] & (1 << ((sum >> 1) & 31))) != 0)){
                        map[prod] = 1;
                        res -= prod;
                }
            }
        }
        System.out.println(1+ res);
    }
}


// better way

public class Problem357 {
	public static void main(String[] args) {
		System.out.println(run());
	}
	private static final int N = 100000000;
	private static int[] prime;
	static String run() {
	    prime = new int[(N>>5) + 1]; 
        int lim = (int) Math.sqrt(N);
        for (int i = 3; i  <= lim; i += 2) { 
            if ((prime[i>>5] & (1 << ((i >> 1) & 31))) == 0) {
                for (int j = i * i, k = i << 1; j < N; j += k) 
                    prime[j>>5] |= (1 << ((j >> 1) & 31));
            }
        } 
		long sum = 0;
		for (int n = 0; n <= N; n++) {
			if (isPrime(n + 1) && generatesPrime(n)){
				sum += n;
			}
		}
		return Long.toString(sum+1);
	}
	
	static boolean isPrime(int i){
		if((i&1) == 0)
			return false;
	    return ((prime[i>>5] & (1 << ((i >> 1) & 31))) == 0);
	}
	static boolean generatesPrime(int n) {
		for (int i = 1, end = (int)Math.sqrt(n); i <= end; i++) {
			if (n % i == 0 && !isPrime(i + n / i))
				return false;
		}
		return true;
	}
}
