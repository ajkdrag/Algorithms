/*
  Problem at : https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1931
  Given an integer 'n', step(n) is defined as the number of steps for 'n' to reach 1.
  Here each step involves computing φ(n) and so on. 
  eg : say n = 13 : 
  φ(13) = 12
  φ(12) = 4
  φ(4) = 2
  φ(2) = 1
  Thus the number of steps needed was 4 i.e step(13) = 4
  Given a range [a,b] we need to compute : summation(step(i)) where 'i' in [a,b]
  
  Algo : 
	  We use Dynamic Programming approach to solve this problem.
	  First we store the φ values for all odd numbers upto the given upperbound of this problem (which is 2000000)
	  We can do this by modified sieve of eratosthenes. 
	  Now for any number 'n' , 
	  
	  Step(n) = 1 + step(phi(n))		-------------> (1)

	  for even number 'n' we have two cases : n = 2*k where 'k' can be either odd or even
	  if 'k' is odd --> step(2*k) = step(k)	-------------> (2) [Since φ(2*k) = φ(k) if 'k' is odd , using this in (1)]
	  if 'k' is even --> step(2*k) = 1 + step(k)	--------------> (3)
	  
	  This can be proved analytically as follows :
	  Let the steps 'k' follows to reach 1 be as follows (let the number of steps be 's')
	  k --> φ(k) --> φ(φ(k)) --> φ(φ(φ(k))) ... 	--> 1
	  
	  Now, we consider 2*k and see it's steps as follows :
	  2*k --> φ(2*k) --> φ(φ(2*k)) --> φ(φ(φ(2*k))) ... 
	  which is equal to :
	  2*k --> 2*φ(k) --> 2*φ(φ(k)) --> 2*φ(φ(φ(k))) ...
	  
	  Thus it is clear that after 's' steps, 2*k will end up at 2*1 i.e 2 and thus it needs one extra step to reach 1
	  
	  Hence, we have : Step(2*k) = 1 + step(k)
	  
	  Thus, using (1), (2) and (3) we can easily come up with a DP solution. We simply compute the Phi values for
	  all the odd numbers upto the limit using our modified sieve.
	  Next, we loop through the array left to right and if the number is odd we use (1). SInce phi(n) is < n always,
	  We must have already computed step(phi(n)) in some previous iteration.
	  Similarly, if 'n' is even, we use (2) and (3) accordingly.
	  
	  To save time on getting the result of queries with different ranges, we convert the array into a prefix sum array
	  and get the result of our queries in O(1)
*/

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws IOException{
        int limit = 2000000;
        SolutionSODF.fillPhiSieve(limit);
        Scan scan = new Scan();
		Print print = new Print();
		int t = scan.scanInt();
		while(t-- > 0){
		    int a = scan.scanInt();
		    int b = scan.scanInt();
		    print.println(SolutionSODF.solve(a,b));
		}
        print.close();
    }
}

class SolutionSODF {
    static int[] phiSieve;
    
    static int solve(int a, int b){
	// getting the result of our query in O(1) because the array phiSieve is now a prefix-summed array
        return phiSieve[b] - phiSieve[a - 1];
    }
    
    static void fillPhiSieve(int limit){
	// len is basically half of our limit
        int len = (limit + 1)>>1;
        phiSieve = new int[limit + 1];
      
	// step(1) = 0 and step(2) = 1 , these are the trivial cases
        phiSieve[1] = 0;
        phiSieve[2] = 1;
        
	// we will be using modified Sieve to calculate the Phi values for the odd numbers
	// we know that for any prime 'p' , φ(p) = p - 1
	// thus using this while loop, we "represent" all odd numbers as "primes" and then we will "cross" out the non-primes
        for(int i = 3; i <= limit; i += 2){
            phiSieve[i] = i - 1;
        }
        
	// we iterate only till limit/2 because a number > 2 that's not prime will have prime factor(s) till number/2
        for(int i = 3; i <= len; i += 2){
	    // phiSieve[i] == i - 1 implies that 'i' is prime (i.e wasn't "crossed off")
            if(phiSieve[i] == i - 1){
		// "cross out" all odd multiples of 'i' (since we only care about odd numbers and primes numbers > 2 are odd anyway)
		// j = (i<<2) - i means j = 3*i (i.e the first odd multiple of 'i') and we increment by i<<1 i.e 2*i for same reason
                for(int j = (i<<2) - i; j <= limit; j += i<<1){
                    int val = phiSieve[j];
		    // if phiSieve[j] == j - 1, it means that this array entry of 'j' hasn't been touched yet, so 
	            // we have to do (j - j/i) . Recall the phi formula as : n(1 - 1/p)(1 - 1/q)(1 - 1/r)...
	            // since we have filled phiSieve[n] with (n - 1) for the 1st time we need n(1 - 1/p)
	            // but we have (n - 1) so we need to check this case and proceed normally after that.
                    if(val == (j - 1)) {
                        phiSieve[j] = j - j/i;
                    }
                    else phiSieve[j] -= val/i;
                }
            }
        }
        
        // Note that in our algo we discussed the case of finding step(2*k) for 'k' both odd and even.
	// We can observe the even numbers as : 2, 4, 6, 8, 10, 12, 14 i.e --> 2*1, 2*2, 2*3, 2*4, 2*5, 2*6, 2*7
	// This shows that 'k' alternates between odd and even so, we can do a trick to get this alternation by using XOR operation
	// flag variable represents whether 'k' is odd or even : flag = 1 ==> k = even ; flag = 0 ==> k = odd
        int flag = 1;
        for(int i = 4; i <= limit; ++i){
            if((i&1) == 0){
                phiSieve[i] += phiSieve[i>>1] + flag; // we combined (2) and (3) using our flag variable
                flag ^= 1;  // use the fact that 'k' alternates between odd and even, so if it's even now, next time it'll be odd.
            }
	    // case (1) when 'n' is odd (note that for odd numbers we already have their Phi values stored)
            else phiSieve[i] = phiSieve[phiSieve[i]] + 1;
        }
        // turn the array into a prefix-summed array for getting the queries in O(1)
        for(int i = 3; i <= limit; ++i){
            phiSieve[i] += phiSieve[i-1];
        }
    }
}

// fast I/O
class Scan
{
    private byte[] buf=new byte[1024];    //Buffer of Bytes
    private int index;
    private InputStream in;
    private int total;
    public Scan()
    {
        in=System.in;
    }
    public int scan()throws IOException    //Scan method used to scan buf
    {
        if(total<0)
        throw new InputMismatchException();
        if(index>=total)
        {
            index=0;
            total=in.read(buf);
            if(total<=0)
            return -1;
        }
        return buf[index++];
    }
    public int scanInt()throws IOException
    {
        int integer=0;
        int n=scan();
        while(isWhiteSpace(n))    //Removing starting whitespaces
        n=scan();
        int neg=1;
        if(n=='-')                //If Negative Sign encounters
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n))
        {
            if(n>='0'&&n<='9')
            {
                integer*=10;
                integer+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*integer;
    }
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
}

class Print
{
    private final BufferedWriter bw;
    public Print()
    {
        this.bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public void print(Object object)throws IOException
    {
        bw.append(""+object);
    }
    public void println(Object object)throws IOException
    {
        print(object);
        bw.append("\n");
    }
    public void close()throws IOException
    {
        bw.close();
    }
}


