/*
  Problem at : http://www.spoj.com/problems/LCMSUM/
  Excellent Description of solution at : https://forthright48.com/2015/08/spoj-lcmsum-lcm-sum.html
  Note that in this modified implementation, while calculating the phi values, we can also compute the lcmsums because it is series
  summation of φ(d)*(d) where 'd' is a divisor of 'n'. When we are looking at the multiples of a number, while forming the sieve,
  we already have computed the results for the numbers upto that number. Thus we can use them to find the lcmsum for the multiples.
  Note that for prime numbers, we can directly get the LCM sum from :
  sum = n*[n*(n-1)/2 + 1] = n/2 * [n*(n - 1) + 2]   ---------> (1)
  while the generalized formula for LCMSUM is : n/2 * (Summation[φ(d) * d] + 1) where 'd' are all positive divisors of 'n' (inclusive)
  Note that '1' is a divisor of every number, thus we can rewrite above as : n/2 * (Summation[φ(d) * d] + 2)  ---------> (2)
  Here 'd' are all positive divisors of 'n' excluding '1'
  Thus (1) and (2) have now been rewritten in a similar form. (even though we don't need to do this, since φ(prime) = prime - 1 anyway)
  
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws IOException{
        Scan input = new Scan();
        Print output = new Print();
        Solution_LCMSUM.fillSieve(1000000);
        
        int t =  input.scanInt();
        while(t-- > 0){
            int n = input.scanInt();
            long res = Solution_LCMSUM.get_lcmSum(n);
            output.println(res);
        }
        
        output.close();
    }
}

class Solution_LCMSUM {
    // two arrays for storing phi values and lcmsums.
    static long[] phi;
    static long[] lcmSums;
    
    static void fillSieve(int n){
        phi = new long[n+1];
        lcmSums = new long[n+1];
        
        // trival cases
        phi[1] = 1;
        lcmSums[1] = 1;
        
        // modified sieve
        for(int i = 2; i <= n; ++i){
            // phi[i] == 0 is an indicator that 'i' is prime
            if(phi[i] == 0){
                // from (1)
                long val = (long)i * (long)(i - 1);
                lcmSums[i] = val;
                // for all multiples of this prime, we can calculate partial phi values as well as partial lcmsums
                // recall φ(n) = (n - n/p)(1 - 1/q)(1 - 1/r)... where p, q, r... are the prime factors of 'n'
                for ( int j = i<<1; j <= n; j += i ) {
                    // initially phi[j] = 0, but we need to make it equal to 'j' for first iteration.
                    if(phi[j] == 0) phi[j] = j;
                    phi[j] -= phi[j]/i;
                    // for lcmSum calculation we need to simply add (φ(d) * d) and for prime (d), we have (d - 1)*d
                    lcmSums[j] += val;
                }
            }
            else {
                 // 'i' is not a prime, but it still is a divisor of (i + i), (i + i + i)... i.e 2*i, 3*i, 4*i ...
                 for ( int j = i; j <= n; j += i ) {
                    // from (2)
                    lcmSums[j] += phi[i]*(long)i;
                }
            }
        }
    }
    
    static long get_lcmSum(int n){
        // we have the partial sums stored in the array, to get the final result,
        // complete the formula by adding and multiplying accordingly. Refer to (2)
        // we have the 'Summation[φ(d) * d]' part stored in lcmSums. We need to add '2' and then multiply with 'n' and so on.
        long ans = lcmSums[n] + 2;
        ans *= n;
        ans >>= 1;
        return ans;
    }
}

// fast IO
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

